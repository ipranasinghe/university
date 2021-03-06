package com.project.ums.services;

import com.project.ums.controllers.dto.DepartmentEnrollmentDTO;
import com.project.ums.controllers.dto.SubjectEnrollmentDTO;
import com.project.ums.controllers.dto.UserDTO;
import com.project.ums.models.*;
import com.project.ums.repository.ProfileRepository;
import com.project.ums.repository.RoleRepository;
import com.project.ums.repository.SubjectRepository;
import com.project.ums.repository.UserRepository;
import com.project.ums.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public SubjectRepository subjectRepository;

    @Autowired
    public ProfileRepository profileRepository;

    public List<UserDTO> findAll(){
        var users =  userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user:
             users) {
            var dto = convert(user);
            userDTOList.add(dto);
        }

        return userDTOList;
    }

    public List<UserDTO> findUsersExceptAdmins(){

        var users =  userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user:
                users) {
            var dto = convert(user);
            if(dto.getType() != 1){
                userDTOList.add(dto);
            }
        }

        return userDTOList;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public UserDTO convert(User user) {
        var dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getUserName());
        dto.setRole(user.getRoles().iterator().next().getName());

        if(user.getProfile() != null){
            dto.setEmail(user.getProfile().getEmail());
            dto.setNic(user.getProfile().getNic());
            if(user.getProfile() instanceof Student){
                dto.setType(3);
            }
            else if(user.getProfile() instanceof Lecturer){
                dto.setType(2);
            }
            else{
                dto.setType(1);
            }
        }
        else{
            dto.setType(1);
        }

        if(user.getSubjects() != null){
            dto.setSubjects(Utils.convertSetToList(user.getSubjects()));
        }
        else{
            dto.setSubjects(new ArrayList<>());
        }

        if(user.getMails() != null){
            dto.setMails(Utils.convertSetToList(user.getMails()));
        }
        else{
            dto.setMails(new ArrayList<>());
        }

        return dto;
    }

    public User convertToUser(UserDTO userDTO){
        var user = new User();

        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setUserName(userDTO.getUserName());
        user.setEnabled(true);

        if (userDTO.getType() == 1){
            var profile = new Admin();
            profile.setEmail(userDTO.getEmail());
            profile.setNic(userDTO.getNic());
            profile.setName(userDTO.getName());

            var role = roleRepository.findRoleByName("ADMIN");
            user.setRoles(Set.of(role));
            user.setProfile(profile);
        }
        else if (userDTO.getType() == 2){
            var profile = new Lecturer();
            profile.setEmail(userDTO.getEmail());
            profile.setNic(userDTO.getNic());
            profile.setName(userDTO.getName());
            profile.setEmployeeNo(userDTO.getEmployeeNumber());

            var role = roleRepository.findRoleByName("LECTURER");
            user.setRoles(Set.of(role));
            user.setProfile(profile);

        }else{
            var profile = new Student();
            profile.setEmail(userDTO.getEmail());
            profile.setNic(userDTO.getNic());
            profile.setName(userDTO.getName());
            profile.setIndexNumber(userDTO.getIndexNumber());

            var role = roleRepository.findRoleByName("STUDENT");
            user.setRoles(Set.of(role));
            user.setProfile(profile);
        }

        return user;

    }

    public void save(UserDTO userDTO) {
        var user = convertToUser(userDTO);
        if(user.getProfile() != null){
            var profile = profileRepository.save(user.getProfile());
            user.setProfile(profile);
        }

        userRepository.save(user);
    }

    public void delete(User user) {
        for (Role role : user.getRoles()) {
            user.removeRole(role);
        }

        for (Subject subject : user.getSubjects()) {
            user.removeSubjects(subject);
        }

        userRepository.delete(user);
    }

    public void addSubjectEnrollment(SubjectEnrollmentDTO dto) {

        User user = userRepository.findById(dto.getUserId()).get();
        Subject subject = subjectRepository.findById(dto.getSubjectId()).get();
        var subjects = user.getSubjects();
        subjects.add(subject);
        user.setSubjects(subjects);
        userRepository.save(user);
    }


    public List<UserDTO> EnrolledStudents(List<Integer> subjects){

        var allUsers = this.findAll();

        List<UserDTO> students = new ArrayList<>();


        for (UserDTO user: allUsers) {
            List<Integer> common = new ArrayList<Integer>(subjects);
            var userSubjects = user.getSubjects().stream().map((subject) -> subject.getId()).collect(Collectors.toList());
            common.retainAll(userSubjects);

            if(common.size() > 0 && user.getType() == 3){
                students.add(user);
            }

        }

        return students;
    }

   public UserDTO getUserDTO(Long id){
        var user = userRepository.findById(id).get();

        return convert(user);
   }


}
