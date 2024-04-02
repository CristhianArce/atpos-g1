package com.atpos.atpos.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElevationOfPrivilegesDetectedFaultsService {

    private ElevationOfPrivilegesDetectedFaultsRepository elevationOfPrivilegesDetectedFaultsRepository;

    public ElevationOfPrivilegesDetectedFaultsService(
            ElevationOfPrivilegesDetectedFaultsRepository elevationOfPrivilegesDetectedFaultsRepository) {
        this.elevationOfPrivilegesDetectedFaultsRepository = elevationOfPrivilegesDetectedFaultsRepository;
    }

    @Transactional
    public void detectedFault(String faultDescription, double elapsedTime, String severity, String username){
        var fault = new ElevationOfPrivilegesDetectedFaults();
        fault.setFaultDescription(faultDescription);
        fault.setElapsedTime(elapsedTime);
        fault.setSeverity(severity);
        fault.setUsername(username);
        elevationOfPrivilegesDetectedFaultsRepository.save(fault);
    }
}
