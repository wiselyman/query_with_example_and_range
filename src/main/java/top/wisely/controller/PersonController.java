package top.wisely.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wisely.domain.Person;
import top.wisely.repository.PersonRepository;
import top.wisely.repository.support.Range;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by wangyunfei on 2017/6/6.
 */
@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        Person p = personRepository.save(person);
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<Person>> query(Person person,
                                              @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                              @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
                                              Integer startHeight,
                                              Integer endHeight,
                                              Pageable pageable){
        Example<Person> personExample = Example.of(person);

        List<Range<Person>> ranges = newArrayList();
        Range<Person> birthRange = new Range<Person>("birthday",startDate,endDate);
        Range<Person> heightRange = new Range<Person>("height",startHeight,endHeight);
        ranges.add(birthRange);
        ranges.add(heightRange);

        Page<Person> page = personRepository.queryByExampleWithRange(personExample,ranges,pageable);

        return new ResponseEntity<Page<Person>>(page,HttpStatus.OK);

    }
}
