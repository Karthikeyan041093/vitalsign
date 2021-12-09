package com.example.i2i.VitalSignManagement.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.i2i.VitalSignManagement.dto.PatientDTO;
import com.example.i2i.VitalSignManagement.dto.UserDTO;
import com.example.i2i.VitalSignManagement.dto.VitalSignDTO;
import com.example.i2i.VitalSignManagement.entity.VitalSign;
import com.example.i2i.VitalSignManagement.repository.VitalSignRepository;
import com.example.i2i.VitalSignManagement.service.VitalSignService;

@Service
public class VitalSignServiceImpl implements VitalSignService {

	private VitalSignRepository vitalSignRepo;

	private ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public VitalSignServiceImpl(VitalSignRepository vitalSignRepo, ModelMapper modelMapper, RestTemplate restTemplate) {
		super();
		this.vitalSignRepo = vitalSignRepo;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
	}

	@Override
	public VitalSignDTO saveVitalSignDtls(VitalSignDTO vitalSignDTO) {
		getUserAndPatDetails(vitalSignDTO);
		VitalSign vitalSign = convertToDBObj(vitalSignDTO);
		return convertToDtoObj(vitalSignRepo.save(vitalSign));
	}

	private void getUserAndPatDetails(VitalSignDTO vitalSignDTO) {

		int userId = vitalSignDTO.getUserid();
		int patientId = vitalSignDTO.getPatientID();

		String plainCreds = "admin:nimda";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		String userByIdURI = "http://localhost:8080/userroles/api/user/" + userId;
		String patByIdURI = "http://localhost:8090/patientdetails/api/patient/" + patientId;

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<PatientDTO> patResponse = restTemplate.exchange(patByIdURI, HttpMethod.GET, request,
				PatientDTO.class);
		PatientDTO patientDTO = patResponse.getBody();

		request = new HttpEntity<String>(headers);
		ResponseEntity<UserDTO> userResponse = restTemplate.exchange(userByIdURI, HttpMethod.GET, request,
				UserDTO.class);
		UserDTO userDTO = userResponse.getBody();

		System.out.println("Inside saveVitalSignDtls Method");

		vitalSignDTO.setPatientName(patientDTO.getPatientFirstName());
		vitalSignDTO.setUserName(userDTO.getUserName());

	}

	private VitalSignDTO convertToDtoObj(VitalSign vitalSign) {
		ModelMapper modelMapper = new ModelMapper();
		VitalSignDTO vitalSignDTO = modelMapper.map(vitalSign, VitalSignDTO.class);
		return vitalSignDTO;
	}

	private VitalSign convertToDBObj(VitalSignDTO vitalSignDTO) {
		VitalSign vitalSign = modelMapper.map(vitalSignDTO, VitalSign.class);
		return vitalSign;
	}

	@Override

	@CachePut(value = "VitalSignDTO", key = "#vitalSignDTO.vitalSignId")
	public VitalSignDTO updateVitalSign(VitalSignDTO vitalSignDTO) {
		System.out.println("Inside updateVitalSign Method");
		getUserAndPatDetails(vitalSignDTO);
		VitalSign vitalSign = convertToDBObj(vitalSignDTO);
		return convertToDtoObj(vitalSignRepo.save(vitalSign));
	}

	@Override
	@CachePut(value = "VitalSignDTO", key = "#vitalSignDTO.vitalSignId")
	public VitalSignDTO patchUpdateVitalSign(int id, Map<Object, Object> vitSignObj) {
		System.out.println("Inside patchUpdateVitalSign Method");
		Optional<VitalSign> existingVitalSign = vitalSignRepo.findById(id);
		if (existingVitalSign.isPresent()) {
			vitSignObj.forEach((key, value) -> {
				Field field = ReflectionUtils.findRequiredField(VitalSign.class, (String) key);
				field.setAccessible(true);
				System.out.println(key + "--->" + field.getName() + "---->" + value);
				ReflectionUtils.setField(field, existingVitalSign.get(), value);
			});
			VitalSign updatedVitalSign = vitalSignRepo.save(existingVitalSign.get());
			return convertToDtoObj(updatedVitalSign);
		} else {
			return null;
		}

	}

	@Override
	@Cacheable(value="VitalSignDTO")
	public List<VitalSignDTO> getAllVitalSignDetails() {
		System.out.println("Inside getAllVitalSignDetails Method");
		Iterable<VitalSign> vitalSignList = vitalSignRepo.findAll();
		List<VitalSign> result = StreamSupport.stream(vitalSignList.spliterator(), false).collect(Collectors.toList());
		if (result.isEmpty()) {
			return new ArrayList<VitalSignDTO>();
		} else {
			return result.stream().map(this::convertToDtoObj).collect(Collectors.toList());
		}
	}

	@Override
	@Cacheable(value="VitalSignDTO")
	public List<VitalSignDTO> getAllVitalSignWithPagination(Integer pageNo, Integer pageSize) {
		System.out.println("Inside getAllVitalSignWithPagination Method");
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<VitalSign> pagedResult = vitalSignRepo.findAll(pageable);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent().stream().map(this::convertToDtoObj).collect(Collectors.toList());
		} else {
			return new ArrayList<VitalSign>().stream().map(this::convertToDtoObj).collect(Collectors.toList());
		}
	}

	@Override
	@Cacheable(value="VitalSignDTO",key="#id")
	public VitalSignDTO findVitalSignById(int id) {
		System.out.println("Inside findById Method");
		VitalSign vitalSign = null;
		Optional<VitalSign> vitSignById = vitalSignRepo.findById(id);
		if (vitSignById.isPresent()) {
			vitalSign = vitSignById.get();
			return convertToDtoObj(vitalSign);
		} else {
			return null;
		}

	}

	@Override
	@CacheEvict(value="VitalSignDTO",key="#id",allEntries = true)
	public void deleteVitalSignById(int id) {
		System.out.println("Inside deleteVitalSignById Method");
		vitalSignRepo.deleteById(id);

	}

}
