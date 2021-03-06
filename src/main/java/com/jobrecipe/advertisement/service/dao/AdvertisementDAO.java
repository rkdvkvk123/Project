package com.jobrecipe.advertisement.service.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.jobrecipe.advertisement.vo.AdvertisementVO;
import com.jobrecipe.advertisement.vo.Paging;
import com.jobrecipe.advertisement.vo.SearchCriteria;

@Repository
public interface AdvertisementDAO {
	
	ArrayList<AdvertisementVO> searchInfo() throws Exception; 
		
	ArrayList<AdvertisementVO> cInfo_Date(Paging pa) throws Exception; 
	
	AdvertisementVO aInfo(AdvertisementVO vo) throws Exception;
	
	ArrayList<AdvertisementVO> cInfo_Star(Paging pa) throws Exception; 
	
	int listCount() throws Exception;

	ArrayList<AdvertisementVO> countInfo();

	ArrayList<AdvertisementVO> searchTitle(SearchCriteria scri);
	
	int searchList(SearchCriteria scri);

/*	void UpdateSave(AdvertisementVO vo);

	void UpdateSaveCancel(AdvertisementVO vo);*/
	
	AdvertisementVO selectSaveOne(AdvertisementVO vo);

	void UpdateCount(AdvertisementVO vo);

	AdvertisementVO cInfo_Date_Vo(Paging pa);

	AdvertisementVO cInfo_Star_Vo(Paging pa);

	ArrayList<AdvertisementVO> allAdvertise(Paging cri);
	
	AdvertisementVO selectOne(int ad_no);

	

}
