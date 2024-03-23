package com.atpos.atpos.healtcheck;

import com.atpos.atpos.utils.ATPosServerResponse;
import com.atpos.atpos.utils.ServerResponse;
import com.atpos.atpos.utils.ServerResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class HealthCheckService {

    private final DataBaseCheckRepo dataBaseCheckRepo;
    private final ServerResponseRepository serverResponseRepository;
    private static final Logger logger = Logger.getLogger(HealthCheckService.class.getName());


    public HealthCheckService(DataBaseCheckRepo dataBaseCheckRepo, ServerResponseRepository serverResponseRepository) {
        this.dataBaseCheckRepo = dataBaseCheckRepo;
        this.serverResponseRepository = serverResponseRepository;
    }

    public ATPosServerResponse check() throws InterruptedException {
        String state;
        int addedTimeInMilliseconds;
        Random random = new Random();
        int stateRandomness = random.nextInt(3);
        if (stateRandomness == 1) {
            state = "UP";
        } else {
            state = "DOWN";
        }

        if (state.equals("UP")){
            try {
                if(dataBaseCheckRepo.selectOne() == 1) {
                    logger.info("DataBase Server is Working fine.");
                } else {
                    logger.info("DataBase Server is not available.");
                }
            } catch (Exception e) {
              state = "DOWN";
              logger.info("DataBase Server is not available.");
            }
        }
        int randomIndex = random.nextInt(2);
        addedTimeInMilliseconds = randomIndex * 1000;
        Thread.sleep(addedTimeInMilliseconds);
        serverResponseRepository.save(keepRecord(addedTimeInMilliseconds, state));
        return new ATPosServerResponse(state);
    }

    private ServerResponse keepRecord(int addedTime, String state) {
        var sr = new ServerResponse();
        sr.setAddedTime(addedTime);
        sr.setStatus(state);
        return sr;
    }

}
