package com.rightfindpro.become.choice;

//import com.rightfindpro.become.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    public List<Choice> findAllByQuestion(Integer id);

    List<Choice> findAllChoiceByQuestionId(@Param("id")Integer id);
    public Choice findAllById(Integer id);

    public void deleteChoiceByExamsIsNull();






}
