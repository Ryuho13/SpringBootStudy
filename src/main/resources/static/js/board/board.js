/**
 * 
 */

const btn = document.getElementById("fileAdd");
const files = document.getElementById("files");

let count = 1;

files.addEventListener("click", (e) => {
	let d = e.target;
	if(d.classList.contains("del")){
		d.parentElement.remove();
		count--;
	}
})


fileAdd.addEventListener("click", () =>{
	if(count > 5){
		alert("최대 5개 까지만");
		return;
	}
	let div = document.createElement("div");
	let input = document.createElement("input");
	input.type = "file";
	input.name = "attach";
	let button = document.createElement("button");
	button.classList.add("del");
	button.type = "button"
	button.innerText="X"
	
	div.append(input);
	div.append(button);
	
	files.append(div);
	
	count++;
})
