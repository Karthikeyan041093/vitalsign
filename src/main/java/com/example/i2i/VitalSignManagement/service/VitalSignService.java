package com.example.i2i.VitalSignManagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.i2i.VitalSignManagement.dto.VitalSignDTO;

@Component
public interface VitalSignService {

	VitalSignDTO saveVitalSignDtls(VitalSignDTO vitalSignDTO);

	VitalSignDTO updateVitalSign(VitalSignDTO vitalSignDTO);

	VitalSignDTO patchUpdateVitalSign(int id, Map<Object, Object> vitSignObj);

	List<VitalSignDTO> getAllVitalSignDetails();

	List<VitalSignDTO> getAllVitalSignWithPagination(Integer pageNo, Integer pageSize);

	VitalSignDTO findVitalSignById(int id);

	void deleteVitalSignById(int id);

}
