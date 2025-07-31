package ca.on.gov.edu.coreft.service;

import ca.on.gov.edu.coreft.StudentProfile;
import ca.on.gov.edu.coreft.util.MyBatisUtil;
import com.ibatis.sqlmap.client.SqlMapSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    
    private static final Logger logger = Logger.getLogger(StudentService.class);
    
    public List<StudentProfile> getAllStudents() {
        logger.info("Getting all students from database");
        SqlMapSession session = null;
        List<StudentProfile> students = new ArrayList<>();
        
        try {
            session = MyBatisUtil.getSqlMapClient().openSession();
            students = (List<StudentProfile>) session.queryForList("com.azure.sample.StudentMapper.listStudent");
            logger.info("Retrieved " + students.size() + " students");
        } catch (Exception ex) {
            logger.error("Error retrieving students: " + ex.getMessage(), ex);
            // Return empty list in case of error
            students = new ArrayList<>();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception e) {
                    logger.error("Error closing session: " + e.getMessage(), e);
                }
            }
        }
        
        return students;
    }
}
