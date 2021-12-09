package com.example.i2i.VitalSignManagement.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.i2i.VitalSignManagement.dto.VitalSignDTO;
import com.example.i2i.VitalSignManagement.service.VitalSignService;

@RestController
@RequestMapping("/api")
public class VitalSignController {
	VitalSignService vitalSignService;

	public VitalSignController(VitalSignService vitalSignService) {
		super();
		this.vitalSignService = vitalSignService;
	}

	@PostMapping("/vitalsign")
	public ResponseEntity<VitalSignDTO> saveVitalSignDtls(@RequestBody VitalSignDTO vitalSignDTO) {
		try {
			VitalSignDTO _vitalSignDTO = vitalSignService.saveVitalSignDtls(vitalSignDTO);
			return new ResponseEntity<>(_vitalSignDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/vitalsign")
	public ResponseEntity<VitalSignDTO> updateVitalSign(@RequestBody VitalSignDTO vitalSignDTO) {
		try {
			VitalSignDTO _VitalSignDTO = vitalSignService.updateVitalSign(vitalSignDTO);
			return new ResponseEntity<>(_VitalSignDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("/vitalsign/{id}")
	public ResponseEntity<VitalSignDTO> patchUpdateVitalSign(@PathVariable("id") int id,
			@RequestBody Map<Object, Object> vitSignObj) {
		try {
			VitalSignDTO _VitalSignDTO = vitalSignService.patchUpdateVitalSign(id, vitSignObj);
			return new ResponseEntity<>(_VitalSignDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/vitalsign", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<VitalSignDTO>> getAllVitalSignDetails() {
		List<VitalSignDTO> listOfVitalSignDtls = new ArrayList<VitalSignDTO>();
		try {
			listOfVitalSignDtls = vitalSignService.getAllVitalSignDetails();
			if (listOfVitalSignDtls.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listOfVitalSignDtls, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/vitalsign/page", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<VitalSignDTO>> getVitalSignWithPagination(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "1") Integer pageSize) {
		List<VitalSignDTO> listOfVitalSignDtls = new ArrayList<VitalSignDTO>();
		try {
			listOfVitalSignDtls = vitalSignService.getAllVitalSignWithPagination(pageNo, pageSize);
			if (listOfVitalSignDtls.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listOfVitalSignDtls, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("vitalsign/{id}")
	public ResponseEntity<VitalSignDTO> getVitalSignById(@PathVariable("id") int id) {
		VitalSignDTO _vitalSignDTO = vitalSignService.findVitalSignById(id);

		if (_vitalSignDTO != null) {
			return new ResponseEntity<>(_vitalSignDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("vitalsign/{id}")
	public ResponseEntity<String> deleteVitalSignById(@PathVariable("id") int id) {

		vitalSignService.deleteVitalSignById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
