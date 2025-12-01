package com.winter.app.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager; // Pager import 추가

@Mapper
public interface ProductDAO {
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public Long count(Pager pager) throws Exception;
	
	public int add(ProductDTO productDTO) throws Exception;
	
	public int update(ProductDTO productDTO) throws Exception;
	
	public int delete(ProductDTO productDTO) throws Exception;

}