package com.verizon.vnf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verizon.vnf.model.FormData;
import com.verizon.vnf.model.PopulateNsd;
import com.verizon.vnf.model.Vnfd;
import com.verizon.vnf.repository.RepositoryImplClass;
import com.verizon.vnf.util.Util;

import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/vnf")
public class VNFController {
	
	private static final String VNF = "VNF";
	@Autowired
	RepositoryImplClass repositoryImplClass;
	
	@Autowired
	Util util;

/*	@RequestMapping(value = "{title}/get", method = RequestMethod.GET)	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Vnf get(@PathVariable String title){
		Vnf vnf = new Vnf();
		vnf.setCompanyname("");
		vnf.setHighleveldes("");
		vnf.setNetworkservice("");
		vnf.setVnfproductname("");
		CompanyTechnicalContact companyTechnicalContact = new CompanyTechnicalContact();
		companyTechnicalContact.setEmail("");
		companyTechnicalContact.setPhone("");
		vnf.setCompanytechnicalcontact(companyTechnicalContact);
		repositoryImplClass.save(VNF, title, vnf);
		return vnf;
	}
	*/
	@RequestMapping(value = "{id}/saveFormData", method = RequestMethod.POST, consumes = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> save(@PathVariable String id, @RequestBody FormData formData){
		
		Map<String,String> message = new HashMap<String,String>();		
		/*Object object = repositoryImplClass.get(VNF, id);
		if(object !=null){
			message.put("type", "failure");
			message.put("message", "Already available!");
			return message;
		}*/
		repositoryImplClass.save(VNF, id, formData);
		message.put("type", "sucess");
		message.put("message", "Data save sucessfully!");
		return message;
	/*	message.put("testing", "add");
		message.put("testing1", "add1");
		message.put("testing2", "add2");
		message.put("testing3", "add3");
		message.put("testing4", "add4");
		
		Map<String,String> message1 = new HashMap<String,String>();
		message1.put("testing2", "add2");
		message1.put("testing3", "add3");
		message1.put("testing4", "add4");
		
		Map<String,String> message2 = new HashMap<String,String>();
		message2.put("testing2", "add2");
		message2.put("testing3", "add3");
		message2.put("testing4", "add4");
		
		Map<String,Map<String,String>> finalc = new HashMap<String,Map<String,String>>();
		finalc.put("1", message);
		finalc.put("2", message1);
		finalc.put("3", message2);
		
		formData.setFormData(finalc);	*/ 
		
		
	}
	
	@RequestMapping(value = "{id}/getFormData", method = RequestMethod.GET, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Object get(@PathVariable String id){		
		System.out.println("Id: "+id);
		Object object = repositoryImplClass.get(VNF, id);		
		return object;	
		
	}	
	
	@RequestMapping(value = "/getAllPackage", method = RequestMethod.GET, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,Object> getAll(){		
		Map<String,Object> listMap = new LinkedHashMap<String,Object>();
		
		List<Object> object = repositoryImplClass.getAll(VNF);
		Set<String> keys = repositoryImplClass.getAllKeys(VNF);
		Iterator<Object> obj = object.iterator();
		Iterator<String> key = keys.iterator();
		while(obj.hasNext() && key.hasNext()){
			listMap.put(key.next(), obj.next());
		}
		return listMap;	
		
	}
	@RequestMapping(value = "{id}/deleteForm", method = RequestMethod.DELETE, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> deleteForm(@PathVariable String id){		
		Map<String,String> message = new HashMap<String,String>();		
		Long status = repositoryImplClass.delete(VNF, id);
		System.out.println("Status: "+status);
		message.put("type", "sucess");
		message.put("message", "Form deleted sucessfully!");
		return message;
	}
	
	@RequestMapping(value = "/createVnfd", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> CreateVnfd(@RequestBody Vnfd vnfd){	
		Map<String,String> message = new HashMap<String,String>();
		
		util.createVnfd("C:\\Users\\TEST\\Desktop\\vnfOnboarding\\openimscore-packages-master", vnfd.getVnfdName(), vnfd.getVendor(), vnfd.getVersion(), vnfd.getType(), vnfd.getEndpoint(), vnfd.getVmImage(), vnfd.getVim(), vnfd.getScaleInOut(), vnfd.getFloatingIp(), vnfd.getFlavor());
		
		return message;
		
	}
	
	@RequestMapping(value = "{vnfName}/uploadVnfd", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> UploadVnfd(@PathVariable String vnfName,@RequestPart MultipartFile uploadFile){	
		Map<String,String> message = new HashMap<String,String>();
		//"C:\\Users\\TEST\\Desktop\\vnfOnboarding\\openimscore-packages-master\\scscf"
		//util.uploadVnfdPackage(uploadFile, vnfName);
		message.put("type", "sucess");
		message.put("message", "sucessfully uploaded");
		return message;		
	}
	
	@RequestMapping(value = "{vnfName}/getVnfStatus", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> getVnfStatus(@PathVariable String vnfName){	
		Map<String,String> message = new HashMap<String,String>();
		//"C:\\Users\\TEST\\Desktop\\vnfOnboarding\\openimscore-packages-master\\scscf"
		String status = util.getVnfStatus(vnfName);
		message.put("type", "sucess");
		message.put("message", "sucessfully get Status");
		message.put("status", status);
		return message;		
	}
	
	@RequestMapping(value = "/createNsd", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> createNsd(@RequestBody PopulateNsd populateNsd){
		
		Map<String,String> message = new HashMap<String,String>();		
		message = util.populateNsd(populateNsd.getVnfdList(),"C:\\Users\\TEST\\Desktop\\vnfOnboarding\\openimscore-packages-master\\descriptors\\tutorial-ims-NSR");		
		return message;		
	}
	
	@RequestMapping(value = "/uploadNsd", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> uploadNsd(@RequestPart MultipartFile uploadFile) throws ParseException{
		
		Map<String,String> message = new HashMap<String,String>();	
		//util.uploadNsd(uploadFile);
		message.put("type", "sucess");
		message.put("message", "sucessfully upload NSD");		
		return message;		
	}	
	
	@RequestMapping(value = "{nsdName}/activateVnf", method = RequestMethod.POST, produces = "application/json")	
	@ApiOperation(value = "This API used to provide the vnf information", notes = "Returns success or failure SLA:500")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful get all the data"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "given Transaction ID does not exist"), })
	public Map<String,String> activateVnf(@PathVariable String nsdName){
		Map<String,String> message = new HashMap<String,String>();		
		util.activateVNF(nsdName);
		message.put("type", "sucess");
		message.put("message", "sucessfully activate Vnf");
		return message;
		
	}
	
}
		
		