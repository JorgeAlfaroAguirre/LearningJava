package com.ltp.gradesubmission;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepository;
    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest(){
        //Arrange
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
                new Grade("Warren", "Tenderness", "A+"),
                new Grade("Sofia", "Fatness", "A+"),
                new Grade("Pepita", "Cuteness", "B+")
        ));
        //Act
        List<Grade> result = gradeService.getGrades();
        //Assert
        assertEquals("Warren", result.get(0).getName());
        assertEquals("Fatness", result.get(1).getSubject());
        assertEquals("B+", result.get(2).getScore());
    }

    @Test
    public void gradeIndexTest(){
        //Arrange
        Grade grade = new Grade("Warren", "Tenderness", "A+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);
        //Act
        int valid = gradeService.getGradeIndex(grade.getId());
        int notFound = gradeService.getGradeIndex("123");
        //Assert
        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        Grade grade = new Grade("Sofia", "Fatness", "A+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);
        assertEquals(grade, result);
    }

    @Test
    public void addGradeTest() {
        Grade grade = new Grade("Sofia", "Fatness", "A+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Pepita", "Cuteness", "B+");
        gradeService.submitGrade(newGrade);
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        Grade grade = new Grade("Pepita", "Cuteness", "B+");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        grade.setScore("A-");
        gradeService.submitGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(grade, 0);

    }
}
