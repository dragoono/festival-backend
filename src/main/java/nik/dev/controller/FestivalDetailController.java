package nik.dev.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nik.dev.model.Festival;
import nik.dev.model.FestivalDetail;
import nik.dev.repository.IFestivalDetailRepository;
import nik.dev.repository.IFestivalRepository;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class FestivalDetailController {
	@Autowired
	private IFestivalRepository festivalRepository;
	@Autowired
	private IFestivalDetailRepository festivalDetailRepository;
	@Autowired
	private PushController pushController;
	
	@RequestMapping(value="festivalDetails", method = RequestMethod.POST)
	public FestivalDetail create(@RequestBody FestivalDetail festival) {
		return festivalDetailRepository.save(festival);
	}
	@RequestMapping(value="festivalDetails", method = RequestMethod.GET)
	public Iterable<FestivalDetail> list() {
		return festivalDetailRepository.findAll();
	}
	@RequestMapping(value="festivalDetails/{id}", method = RequestMethod.GET)
	public FestivalDetail get(@PathVariable Long id) {
		return festivalDetailRepository.findOne(id);
	}
	
	@RequestMapping(value="festivalDetails/{id}", method = RequestMethod.PUT)
	public FestivalDetail update(@RequestBody FestivalDetail festivalDetail) {
		//Festival festival = festivalRepository.findOne(festivalDetail.getFestival_id());
		//pushController.send("Aenderungen des Festivals: "+festival.getName(), "Die Details haben sich geändert");
		return festivalDetailRepository.save(festivalDetail);
	}
	
	@RequestMapping(value="festivalDetails/{id}", method = RequestMethod.DELETE)
	public FestivalDetail delete(@PathVariable Long id) {
		FestivalDetail existingFestivalDetail = festivalDetailRepository.findOne(id);
		festivalDetailRepository.delete(existingFestivalDetail);
		return existingFestivalDetail;
	}
	@RequestMapping(value="festivalDetailsByUnSync", method= RequestMethod.GET)
	public List<FestivalDetail> listUnsync(){
		return festivalDetailRepository.findBySyncStatus("no");                                                                                      
	}
	
}
