package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.util.Pager; // Pager import 추가

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO; 
	
	public List<ProductDTO> list (Pager pager)throws Exception{
		pager.pageing(productDAO.count(pager));
		return productDAO.list(pager);
	}
	
	public ProductDTO detail(ProductDTO productDTO)throws Exception{
		return productDAO.detail(productDTO);
	}
	
	public int add(ProductDTO productDTO)throws Exception{
		return productDAO.add(productDTO);
	}
	
	public int update(ProductDTO productDTO)throws Exception{
		return productDAO.update(productDTO);
	}
	
	public int delete(ProductDTO productDTO)throws Exception{
		return productDAO.delete(productDTO);
	}
}