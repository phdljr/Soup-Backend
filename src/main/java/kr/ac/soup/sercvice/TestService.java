package kr.ac.soup.sercvice;

import kr.ac.soup.repository.TestRepository;
import kr.ac.soup.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public void addTest(){
        Test test = new Test();
        test.setNickname("test1");
        testRepository.save(test);
    }
}