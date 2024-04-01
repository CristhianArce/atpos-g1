package com.atpos.atpos.utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElevationOfPrivilegesDetectedFaultsService {

    private ElevationOfPrivilegesDetectedFaultsRepository elevationOfPrivilegesDetectedFaultsRepository;

    public void setElevationOfPrivilegesDetectedFaultsRepository(
            ElevationOfPrivilegesDetectedFaultsRepository elevationOfPrivilegesDetectedFaultsRepository) {
        this.elevationOfPrivilegesDetectedFaultsRepository = elevationOfPrivilegesDetectedFaultsRepository;
    }

    @Transactional
    public void detectedFault(String faultDescription, double elapsedTime, String severity){
        var fault = new ElevationOfPrivilegesDetectedFaults();
        fault.setFaultDescription(faultDescription);
        fault.setElapsedTime(elapsedTime);
        fault.setSeverity(severity);
        elevationOfPrivilegesDetectedFaultsRepository.save(fault);
    }
}
