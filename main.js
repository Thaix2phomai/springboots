//bài 1
//ý 1
const para = document.getElementById("text")
para.style.color = "#777"

//ý 2
para.style.fontSize = "2rem"

//ý 3
para.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript."

//bài 2

const allLi = document.querySelectorAll("p + ul li")
console.log(allLi)
allLi.forEach(element => {
    element.style.color = "blue"
});

//bài 3
// ý 1 ( thêm )
const li7 = document.querySelector("ul + ul li:last-child")
console.log(li7)
const li8 = document.createElement("li");
li8.innerText = "Item 8";
const li9 = document.createElement("li");
li9.innerText = "Item 9";
const li10 = document.createElement("li");
li10.innerText = "Item 10";
li7.insertAdjacentElement("afterend", li8)
li8.insertAdjacentElement("afterend", li9)
li9.insertAdjacentElement("afterend", li10)


// ý 2 
const li1 = document.querySelector("ul + ul li")
console.log(li1)
li1.style.color = "red"

// ý 3 
const li3 = document.querySelector("ul + ul li:nth-child(3)")
console.log(li3)
li3.style.backgroundColor = "green"

// ý 4 (xóa)
li3.nextElementSibling.parentElement.removeChild(li3.nextElementSibling)

// ý 5 (thêm)
// const newLi4 = document.createElement("li");
// newLi4.innerText = "New Item 4"
li3.insertAdjacentHTML("afterend", "<li> New Item 4 </li>")













