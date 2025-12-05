

console.log("asdaaa")

const list = document.getElementById("list");
let num= list.getAttribute("data-product-num");

fetch(`/product/commentList?productNum=${num}`)
	.then(r =>r.json())
	.then(r =>{
		r.forEach(dto=>{
			console.log()
		})
	})
	.catch(e => console.log(e))	
		
;