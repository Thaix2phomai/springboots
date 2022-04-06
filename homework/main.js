let colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];

let wrap = document.querySelector(".wrap");
let moreBoxes = document.getElementById("btn");
let totalBoxes = document.getElementById("score");
let divBoxes = document.getElementsByClassName("box");

function createFiveColors() {
  for (let i = 0; i < colors.length; i++) {
    let box = document.createElement("div");
    box.className = "box";
    box.style.backgroundColor = colors[i];
    wrap.appendChild(box);
  }
}

function removeBox() {
  for (let i = 0; i < divBoxes.length; i++) {
    divBoxes[i].addEventListener("click", function () {
      this.remove();
    });
  }
}

function new5Boxes() {
  createFiveColors();
  removeBox();
}

function updateTotalBoxes() {
  totalBoxes.innerText = `Total box: ${divBoxes.length}`;
}

createFiveColors();
totalBoxes.innerText = `Total box: ${divBoxes.length}`;
removeBox();

moreBoxes.addEventListener("click", new5Boxes);

document.addEventListener("click", function () {
  totalBoxes.innerText = `Total box: ${divBoxes.length}`;
});
