package com.youthchina.controller.zhongyang;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.*;
import com.youthchina.dto.application.JobApplyDTO;
import com.youthchina.dto.util.PageRequest;
import com.youthchina.exception.zhongyang.ClientException;
import com.youthchina.exception.zhongyang.ForbiddenException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.service.Qinghong.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/21/18.
 */
@RestController
@RequestMapping("${web.url.prefix}/applicants/**")
public class StudentController extends DomainCRUDController<Student, Integer> {
    private String url;
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService, @Value("${web.url.prefix}") String prefix) {
        this.studentService = studentService;
        this.url = prefix + "/applicants/";
    }

    @Override
    protected DomainCRUDService<Student, Integer> getService() {
        return this.studentService;
    }


    @Override
    protected URI getUriForNewInstance(Integer id) throws URISyntaxException {
        return new URI(this.url + id.toString());
    }

    @GetMapping("/{id}/**")
    public ResponseEntity<?> getStudent(@PathVariable Integer id,@AuthenticationPrincipal User user) throws NotFoundException {
        Student student = studentService.get(id);
        student.setId(user.getId());
        student.setIsInJob(user.isHired());
        student.setUsername(user.getUsername());
        student.setAvatarUrl(user.getAvatarUrl());
        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student)));
    }

    @PostMapping("/**")
    @ResponseBodyDTO(ApplicantResponseDTO.class)
    public ResponseEntity<?> createStudentInfo(@AuthenticationPrincipal User user, @RequestBodyDTO(ApplicantRequestDTO.class) Student student) throws NotFoundException {
        student.setId(user.getId());
        Student student1 = studentService.add(student);
        return ResponseEntity.ok(new Response(student1));
    }

    @PutMapping("/{id}/**")
    @ResponseBodyDTO(ApplicantResponseDTO.class)
    public ResponseEntity<?> updateStudentInfo(@RequestBodyDTO(ApplicantRequestDTO.class) Student applicant) throws NotFoundException {
        return update(applicant);
    }

    @DeleteMapping("/{id}/**")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable Integer id) throws NotFoundException {
        return delete(id);
    }

//    @GetMapping("/{id}/contacts")
//    public ResponseEntity<?> getApplicantsContacts(@PathVariable Integer id) throws NotFoundException {
//        Student student = studentService.get(id);
//        return ResponseEntity.ok(new Response(new ApplicantResponseDTO(student).getContacts()));
//    }

    @GetMapping("/{id}/educations")
    public ResponseEntity<?> getApplicantsEducations(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
        List<EducationInfo> educationInfos=studentService.getEducations(id);
        List<EducationResponseDTO> dtos=new ArrayList<>();
        if(educationInfos!=null&&educationInfos.size()!=0){
            for(EducationInfo educationInfo:educationInfos){
                EducationResponseDTO educationResponseDTO=new EducationResponseDTO(educationInfo);
                dtos.add(educationResponseDTO);
            }
        }
        List<EducationResponseDTO> result =dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, educationInfos.size(),result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<?> getApplicantsProjects(@PathVariable Integer id,PageRequest pageRequest) throws NotFoundException {
        List<Project> projects=studentService.getProjects(id);
        List<ProjectResponseDTO> dtos=new ArrayList<>();
        if(projects!=null&&projects.size()!=0){
            for(Project project:projects){
                ProjectResponseDTO projectResponseDTO=new ProjectResponseDTO(project);
                dtos.add(projectResponseDTO);
            }
        }
        List<ProjectResponseDTO> result =dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, projects.size(),result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity<?> getApplicantsExperiences(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
        List<Work> works=studentService.getWorks(id);
        List<WorkResponseDTO> dtos=new ArrayList<>();
        if(works!=null&&works.size()!=0){
            for(Work work:works){
                WorkResponseDTO workResponseDTO=new WorkResponseDTO(work);
                dtos.add(workResponseDTO);
            }
        }
        List<WorkResponseDTO> result=dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, works.size(), result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/certificates")
    public ResponseEntity<?> getApplicantsCertificates(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
        List<Certificate> certificates=studentService.getCertificates(id);
        List<CertificateResponseDTO> dtos=new ArrayList<>();
        if(certificates!=null&&certificates.size()!=0){
            for(Certificate certificate:certificates){
                CertificateResponseDTO certificateResponseDTO=new CertificateResponseDTO(certificate);
                dtos.add(certificateResponseDTO);
            }
        }
        List<CertificateResponseDTO> result=dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, certificates.size(), result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/extracurriculars")
    public ResponseEntity<?> getApplicantsExtracurriculars(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
        List<Activity> activities=studentService.getActivities(id);
        List<ExtracurricularResponseDTO> dtos=new ArrayList<>();
        if(activities!=null&&activities.size()!=0){
            for(Activity activity:activities){
                ExtracurricularResponseDTO extracurricularResponseDTO=new ExtracurricularResponseDTO(activity);
                dtos.add(extracurricularResponseDTO);
            }
        }
        List<ExtracurricularResponseDTO> result=dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse = new ListResponse(pageRequest, activities.size(), result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<?> getApplicantsSkills(@PathVariable Integer id,PageRequest pageRequest) throws NotFoundException {
        List<AdvantageLabel> advantageLabels=studentService.getAdvantageLabel(id);
        List<AdvantageLabelResponseDTO> dtos=new ArrayList<>();
        if(advantageLabels!=null&&advantageLabels.size()!=0){
            for (AdvantageLabel advantageLabel:advantageLabels){
                AdvantageLabelResponseDTO advantageLabelResponseDTO=new AdvantageLabelResponseDTO(advantageLabel);
                dtos.add(advantageLabelResponseDTO);
            }
        }
        List<AdvantageLabelResponseDTO> result=dtos.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,dtos.size()));
        ListResponse listResponse=new ListResponse(pageRequest,advantageLabels.size(),result);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/skills/**")
    public ResponseEntity<?> getAllSkills() throws NotFoundException {
        List<LabelInfo> labelInfos = studentService.getAllSkills();
        List<SkillsResponseDTO> labelInfos1 = new ArrayList<>();
        for (LabelInfo labelInfo : labelInfos) {
            SkillsResponseDTO skillsResponseDTO = new SkillsResponseDTO(labelInfo);
            labelInfos1.add(skillsResponseDTO);
        }

        return ResponseEntity.ok(new Response(labelInfos1));
    }


    /**
     * @Description: 通过user_id获得该用户所有的职位申请的信息
     * @Param: [id]
     * @return: org.springframework.http.ResponseEntity<?>
     * @Author: Qinghong Wang
     * @Date: 2019/2/18
     */
    @GetMapping("/{id}/applications")
    public ResponseEntity<?> getApplicantsJobApplies(@PathVariable Integer id, @AuthenticationPrincipal User user, PageRequest pageRequest) throws NotFoundException, ForbiddenException {
        if (!user.getId().equals(id)) {
            //use access control
            throw new ForbiddenException();
        }
        List<JobApply> jobApplies = studentService.getJobApplies(id);
        List<JobApplyDTO> jobApplyDTOS = new ArrayList<>();
        for (JobApply jobApply : jobApplies) {
            JobApplyDTO jobApplyDTO = new JobApplyDTO(jobApply);
            jobApplyDTOS.add(jobApplyDTO);
        }
        List<JobApplyDTO> result=jobApplyDTOS.subList(pageRequest.getStart(),Math.min(pageRequest.getEnd()+1,jobApplyDTOS.size()));
        ListResponse listResponse = new ListResponse(pageRequest, jobApplyDTOS.size(), result);
        return ResponseEntity.ok(listResponse);
    }


//    /**
//     * @Description: 实现education的保存操作
//     * @Param: [educationRequestDTOS, user]
//     * @return: org.springframework.http.ResponseEntity<?>
//     * @Author: Qinghong Wang
//     * @Date: 2019/2/25
//     */
//
//    @RequestMapping(value = "/{id}/educations", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsEducations(@RequestBody List<EducationRequestDTO> educationRequestDTOS, @AuthenticationPrincipal User user, @PathVariable("id") Integer id) throws NotFoundException {
//        List<EducationInfo> educationInfos = new ArrayList<>();
//        for (EducationRequestDTO educationRequestDTO : educationRequestDTOS) {
//            EducationInfo educationInfo = new EducationInfo(educationRequestDTO);
//            educationInfos.add(educationInfo);
//        }
//        List<EducationInfo> educationInfos1 = studentService.insertEducations(educationInfos, user.getId());
//        List<EducationResponseDTO> educationResponseDTOS = new ArrayList<>();
//        for (EducationInfo educationInfo : educationInfos1) {
//            EducationResponseDTO educationResponseDTO = new EducationResponseDTO(educationInfo);
//            educationResponseDTOS.add(educationResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(educationResponseDTOS));
//    }
//
//    /**
//     * @Description: 实现work的保存操作
//     * @Param: [workRequestDTOS, user]
//     * @return: org.springframework.http.ResponseEntity<?>
//     * @Author: Qinghong Wang
//     * @Date: 2019/2/25
//     */
//
//    @RequestMapping(value = "/{id}/experiences", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsWorks(@RequestBody List<WorkRequestDTO> workRequestDTOS, @AuthenticationPrincipal User user, @PathVariable("id") Integer id, PageRequest pageRequest) throws NotFoundException {
//        List<Work> works = new ArrayList<>();
//        for (WorkRequestDTO workRequestDTO : workRequestDTOS) {
//            Work work = new Work(workRequestDTO);
//            works.add(work);
//        }
//        List<Work> works1 = studentService.insertWorks(works, user.getId());
//        List<WorkResponseDTO> workresponsedtos = new ArrayList<>();
//        for (Work work : works1) {
//            WorkResponseDTO workResponseDTO = new WorkResponseDTO(work);
//            workresponsedtos.add(workResponseDTO);
//        }
//        ListResponse listResponse = new ListResponse(pageRequest, workresponsedtos.size(), workresponsedtos);
//        return ResponseEntity.ok(listResponse);
//    }
//
//    /**
//     * @Description: 实现项目的保存操作
//     * @Param: [projectRequestDTOS, user]
//     * @return: org.springframework.http.ResponseEntity<?>
//     * @Author: Qinghong Wang
//     * @Date: 2019/2/26
//     */
//    @RequestMapping(value = "/{id}/projects", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsProjects(@RequestBody List<ProjectRequestDTO> projectRequestDTOS, @AuthenticationPrincipal User user, @PathVariable("id") Integer id) throws NotFoundException {
//        List<Project> projects = new ArrayList<>();
//        for (ProjectRequestDTO projectRequestDTO : projectRequestDTOS) {
//            Project project = new Project(projectRequestDTO);
//            projects.add(project);
//        }
//        List<Project> projects1 = studentService.insertProjects(projects, user.getId());
//        List<ProjectResponseDTO> projectResponseDTOS = new ArrayList<>();
//        for (Project project : projects1) {
//            ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(project);
//            projectResponseDTOS.add(projectResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(projectResponseDTOS));
//    }
//
//    /**
//     * @Description: 实现课外经历的保存
//     * @Param: [extracurricularRequestDTOS, user]
//     * @return: org.springframework.http.ResponseEntity<?>
//     * @Author: Qinghong Wang
//     * @Date: 2019/2/26
//     */
//    @RequestMapping(value = "/{id}/extracurriculars", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsExtracurriculars(@RequestBody List<ExtracurricularRequestDTO> extracurricularRequestDTOS, @AuthenticationPrincipal User user, @PathVariable("id") Integer id) throws NotFoundException {
//        List<Activity> activities = new ArrayList<>();
//        for (ExtracurricularRequestDTO extracurricularRequestDTO : extracurricularRequestDTOS) {
//            Activity activity = new Activity(extracurricularRequestDTO);
//            activities.add(activity);
//        }
//        List<Activity> activities1 = studentService.insertActivities(activities, user.getId());
//        List<ExtracurricularResponseDTO> extracurricularResponseDTOS = new ArrayList<>();
//        for (Activity activity : activities1) {
//            ExtracurricularResponseDTO extracurricularResponseDTO = new ExtracurricularResponseDTO(activity);
//            extracurricularResponseDTOS.add(extracurricularResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(extracurricularResponseDTOS));
//    }
//
//    /**
//     * @Description: 实现证书信息的保存
//     * @Param: [certificateRequestDTOS, user]
//     * @return: org.springframework.http.ResponseEntity<?>
//     * @Author: Qinghong Wang
//     * @Date: 2019/2/26
//     */
//
//    @RequestMapping(value = "/{id}/certificates", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsCertificates(@RequestBody List<CertificateRequestDTO> certificateRequestDTOS, @AuthenticationPrincipal User user, @PathVariable("id") Integer id, PageRequest pageRequest) throws NotFoundException {
//        List<Certificate> certificates1 = new ArrayList<>();
//        for (CertificateRequestDTO certificateRequestDTO : certificateRequestDTOS) {
//            Certificate certificate = new Certificate(certificateRequestDTO);
//            certificates1.add(certificate);
//        }
//        List<Certificate> certificates = studentService.insertCertificates(certificates1, user.getId());
//        List<CertificateResponseDTO> certificateResponseDTOS = new ArrayList<>();
//        for (Certificate certificate : certificates) {
//            CertificateResponseDTO certificateResponseDTO = new CertificateResponseDTO(certificate);
//            certificateResponseDTOS.add(certificateResponseDTO);
//        }
//        ListResponse listResponse = new ListResponse(pageRequest, certificateResponseDTOS.size(), certificateResponseDTOS);
//        return ResponseEntity.ok(listResponse);
//    }
//
//    @RequestMapping(value = "/{id}/skills", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> saveApplicantsSkills(@RequestBody List<String> skills, @AuthenticationPrincipal User user, @PathVariable("id") Integer id) throws NotFoundException {
//        List<LabelInfo> labelInfos = studentService.insertLabels(skills, id);
//        List<SkillsResponseDTO> skillsResponseDTOS = new ArrayList<>();
//        for (LabelInfo labelInfo : labelInfos) {
//            SkillsResponseDTO skillsResponseDTO = new SkillsResponseDTO(labelInfo);
//            skillsResponseDTOS.add(skillsResponseDTO);
//        }
//        return ResponseEntity.ok(new Response(skillsResponseDTOS));
//
//    }

//
//    @GetMapping("/{id}/resumes")
//    public ResponseEntity<?> getApplicantsResumes(@PathVariable Integer id, PageRequest pageRequest) throws NotFoundException {
//        List<ResumeJson> resumeJsonList = studentService.selectResumeJsonByStuId(id);
//        if (resumeJsonList == null || resumeJsonList.size() == 0) {
//            throw new NotFoundException(404, 404, "No Resume available.");
//        }
//        List<ResumeResponseDTO> resumeResponseDTOList = new ArrayList<>();
//        for (ResumeJson resumeJson : resumeJsonList) {
//            resumeResponseDTOList.add(new ResumeResponseDTO(resumeJson));
//        }
//        ListResponse listResponse = new ListResponse(pageRequest, resumeResponseDTOList.size(), resumeResponseDTOList);
//        return ResponseEntity.ok(listResponse);
//    }

    @PostMapping("/{id}/educations")
    public ResponseEntity<?> insertEducation(@PathVariable Integer id, @RequestBody EducationRequestDTO educationRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            EducationInfo educationInfo = new EducationInfo(educationRequestDTO);
            EducationResponseDTO educationResponseDTO = new EducationResponseDTO(studentService.insertEducation(educationInfo, user.getId()));
            return ResponseEntity.ok(new Response(educationResponseDTO));
        } else {
            throw new ForbiddenException();
        }

    }

    @PostMapping("/{id}/experiences")
    public ResponseEntity<?> insertWork(@PathVariable Integer id, @RequestBody WorkRequestDTO workRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            Work work = new Work(workRequestDTO);
            Work work1 = studentService.insertWork(work, user.getId());
            WorkResponseDTO workResponseDTO = new WorkResponseDTO(work1);
            return ResponseEntity.ok(workResponseDTO);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/{id}/projects")
    public ResponseEntity<?> insertProject(@PathVariable Integer id, @RequestBody ProjectRequestDTO projectRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            Project project = new Project(projectRequestDTO);
            Project project1 = studentService.insertProject(project, user.getId());
            ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(project1);
            return ResponseEntity.ok(projectResponseDTO);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/{id}/certificates")
    public ResponseEntity<?> insertCertificate(@PathVariable Integer id, @RequestBody CertificateRequestDTO certificateRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            Certificate certificate = new Certificate(certificateRequestDTO);
            Certificate certificate1 = studentService.insertCertificate(certificate, user.getId());
            CertificateResponseDTO certificateResponseDTO = new CertificateResponseDTO(certificate1);
            return ResponseEntity.ok(certificateResponseDTO);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/{id}/extracurriculars")
    public ResponseEntity<?> insertExtracurricular(@PathVariable Integer id, @RequestBody ExtracurricularRequestDTO extracurricularRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            Activity activity = new Activity(extracurricularRequestDTO);
            Activity activity1 = studentService.insertActivity(activity, user.getId());
            ExtracurricularResponseDTO extracurricularResponseDTO = new ExtracurricularResponseDTO(activity1);
            return ResponseEntity.ok(extracurricularResponseDTO);
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<?> insertSkill(@PathVariable Integer id, @RequestBody SkillsRequestDTO skillsRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            AdvantageLabel advantageLabel = new AdvantageLabel(skillsRequestDTO);
            AdvantageLabel advantageLabel1 = studentService.insertLabel(advantageLabel, user.getId());
            AdvantageLabelResponseDTO advantageLabelResponseDTO = new AdvantageLabelResponseDTO(advantageLabel1);
            return ResponseEntity.ok(advantageLabelResponseDTO);

        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/educations/{educationID}")
    public ResponseEntity<?> deleteEducation(@PathVariable("id") Integer id, @PathVariable("educationID") Integer edu_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteEducation(edu_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/projects/{projectID}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id, @PathVariable("projectID") Integer proj_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteProject(proj_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/certificates/{certificateID}")
    public ResponseEntity<?> deleteCertificate(@PathVariable("id") Integer id, @PathVariable("certificateID") Integer certificate_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteCertificate(certificate_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/experiences/{experienceID}")
    public ResponseEntity<?> deleteWork(@PathVariable("id") Integer id, @PathVariable("experienceID") Integer work_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteWork(work_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/extracurriculars/{extracurricularID}")
    public ResponseEntity<?> deleteExtracurricular(@PathVariable("id") Integer id, @PathVariable("extracurricularID") Integer act_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteActivity(act_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/{id}/skills/{labelID}")
    public ResponseEntity<?> deleteSkills(@PathVariable("id") Integer id, @PathVariable("labelID") Integer label_id, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException {
        if (user.getId().equals(id)) {
            studentService.deleteLabel(label_id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response());
        } else {
            throw new ForbiddenException();
        }
    }


    @PutMapping("/{id}/educations/{educationID}")
    public ResponseEntity<?> updateEducation(@PathVariable("id") Integer id, @PathVariable("educationID") Integer edu_id, @RequestBody EducationRequestDTO educationRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException, ClientException {
        if (user.getId().equals(id)) {
            if(educationRequestDTO.getId()==null){
                educationRequestDTO.setId(edu_id);
            }
            if(educationRequestDTO.getId()!=edu_id){
                throw new ClientException("can not update because the id difference");
            }
            EducationInfo educationInfo = new EducationInfo(educationRequestDTO);
            EducationInfo educationInfo1 = studentService.updateEducationInfo(educationInfo);
            EducationResponseDTO educationResponseDTO = new EducationResponseDTO(educationInfo1);
            return ResponseEntity.ok(new Response(educationResponseDTO));

        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/{id}/projects/{projectID}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Integer id, @PathVariable("projectID") Integer proj_id, @RequestBody ProjectRequestDTO projectRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException,ClientException {
        if (user.getId().equals(id)) {
            if(projectRequestDTO.getId()==null){
                projectRequestDTO.setId(proj_id);
            }
            if(proj_id!=projectRequestDTO.getId()){
                throw new ClientException("can not update because the id difference");
            }
            Project project = new Project(projectRequestDTO);
            Project project1 = studentService.updateProject(project);
            ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(project1);
            return ResponseEntity.ok(new Response(projectResponseDTO));

        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/{id}/experiences/{experienceID}")
    public ResponseEntity<?> updateWork(@PathVariable("id") Integer id, @PathVariable("experienceID") Integer work_id, @RequestBody WorkRequestDTO workRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException,ClientException {
        if (user.getId().equals(id)) {
            if(workRequestDTO.getId()==null){
                workRequestDTO.setId(work_id);
            }
            if(work_id!=workRequestDTO.getId()){
                throw new ClientException("can not update because the id difference");
            }
            Work work = new Work(workRequestDTO);
            Work work1 = studentService.updateWork(work);
            WorkResponseDTO workResponseDTO = new WorkResponseDTO(work1);
            return ResponseEntity.ok(new Response(workResponseDTO));
        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/{id}/certificates/{certificateID}")
    public ResponseEntity<?> updateCertificate(@PathVariable("id") Integer id, @PathVariable("certificateID") Integer certificate_id, @RequestBody CertificateRequestDTO certificateRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException,ClientException {
        if (user.getId().equals(id)) {
            if (certificateRequestDTO.getId()==null){
                certificateRequestDTO.setId(certificate_id);
            }

            if(certificateRequestDTO.getId()!=certificate_id){
                throw new ClientException("can not update because the id difference");
            }
            Certificate certificate = new Certificate(certificateRequestDTO);
            Certificate certificate1 = studentService.updateCertificate(certificate);
            CertificateResponseDTO certificateResponseDTO = new CertificateResponseDTO(certificate1);
            return ResponseEntity.ok(new Response(certificateResponseDTO));

        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/{id}/extracurriculars/{extracurricularID}")
    public ResponseEntity<?> updateExtracurriculars(@PathVariable("id") Integer id, @PathVariable("extracurricularID") Integer act_id, @RequestBody ExtracurricularRequestDTO extracurricularRequestDTO, @AuthenticationPrincipal User user) throws ForbiddenException, NotFoundException,ClientException {
        if (user.getId().equals(id)) {
            if(extracurricularRequestDTO.getId()==0){
                extracurricularRequestDTO.setId(act_id);
            }
            if(act_id!=extracurricularRequestDTO.getId()){
                throw new ClientException("can not update because the id difference");
            }
            Activity activity = new Activity(extracurricularRequestDTO);
            Activity activity1 = studentService.updateActivity(activity);
            ExtracurricularResponseDTO extracurricularResponseDTO = new ExtracurricularResponseDTO(activity1);
            return ResponseEntity.ok(new Response(extracurricularResponseDTO));

        } else {
            throw new ForbiddenException();
        }
    }


}
