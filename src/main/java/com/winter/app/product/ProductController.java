package com.winter.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.winter.app.util.Pager;

// 현재 파일에는 @RequestMapping이 없으므로, 모든 매핑에 /product/를 붙이거나 환경 설정에 의존합니다.
// 여기서는 기본 매핑이 없는 상태로 path만 수정합니다.
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("list")
	public String list(Pager pager, Model model)throws Exception{

		List<ProductDTO> list= productService.list(pager);
	
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("category", "상품 관리"); // category 값을 상품 관리로 설정
		
		return "product/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "product/add";
	}
	
	@PostMapping("add")
	public ModelAndView add(ProductDTO productDTO, RedirectAttributes redirectAttributes) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    int result = productService.add(productDTO); 
	    
	    if(result > 0) {
	        redirectAttributes.addFlashAttribute("result", "상품 등록 성공"); 
	    } else {
	        redirectAttributes.addFlashAttribute("result", "상품 등록 실패");
	    }
	    mv.setViewName("redirect:./list"); 
	    
	    return mv;
	}
	@GetMapping("detail")
		public ModelAndView detail(ProductDTO productDTO) throws Exception {
			ModelAndView mv = new ModelAndView();
			
			productDTO = productService.detail(productDTO);
			
			mv.addObject("dto", productDTO);
			mv.setViewName("product/detail");
			
			return mv;	
	}
	
	@GetMapping("update") 
    public ModelAndView update(ProductDTO productDTO) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        productDTO = productService.detail(productDTO); 
        
        mv.addObject("dto", productDTO);
        mv.setViewName("product/update"); 
        
        return mv;
    }
	@PostMapping("update")
    public ModelAndView update(ProductDTO productDTO, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        int result = productService.update(productDTO); 
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "상품 정보가 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("result", "상품 정보 수정에 실패했습니다.");
        }
        
        // --- 수정 지점: boardNum -> productNum으로 파라미터 변경 ---
        mv.setViewName("redirect:./detail?productNum=" + productDTO.getProductNum());
        
        return mv;
    }
	
	@PostMapping("delete")
    public ModelAndView delete(ProductDTO productDTO, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView();
        
        int result = productService.delete(productDTO); 
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("result", "상품이 성공적으로 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("result", "상품 삭제에 실패했습니다.");
        }
        
        mv.setViewName("redirect:./list");
        
        return mv;
    }
	

}