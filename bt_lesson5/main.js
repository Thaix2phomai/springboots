const btn = document.getElementById("btn");
const select = document.getElementById("breed-list");
const subBreed = document.querySelector(".sub-breed");
const subBreedElement = document.getElementsByTagName("li");
const imgBox = document.querySelector(".img")

async function getBreedList() {
  try {
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");

    renderBreed(res.data.message);
  } catch (error) {
    console.log(error);
  }
}

function renderBreed(breeds) {
  let keys = Object.keys(breeds);
  // console.log(keys);

  for (let i = 0; i < keys.length; i++) {
    // console.log(breeds[keys[i]]);
    let option = document.createElement("option");
    select.appendChild(option);
    option.innerText = keys[i];
  }

  return keys;
}

btn.addEventListener("click", async function (event) {
  let breed = select.value;
  let res = await axios.get(`https://dog.ceo/api/breed/${breed}/list`);
  console.log(res);
  // console.log(breed)
  if (res.status == 200) {
    const h1 = document.createElement("h1");
    let li = "";
    h1.innerText = "Sub Breed list";
    const ul = document.createElement("ul");
    console.log(res.data.message);
    if (res.data.message.length > 0) {
      res.data.message.forEach((subBreed) => {
        li = document.createElement("li");
        li.innerText = subBreed;
        ul.appendChild(li);
      });
    } else {
      li = document.createElement("li");
      li.innerText = "không có sub breed";
      ul.appendChild(li);
    }
    subBreed.innerHTML = "";
    subBreed.appendChild(h1);
    subBreed.appendChild(ul);
    // console.log(h1)
    // console.log(ul)
  }

  console.log(subBreedElement);
  if (subBreedElement.length > 0) {
    for (const element of subBreedElement) {
      element.addEventListener("click", async e => {
        console.log(e.target.innerText);
        let sub = e.target.innerText
        let img = ""
        let imgRes = await axios.get(`https://dog.ceo/api/breed/${breed}/${sub}/images/random`)
        if (imgRes.status == 200) {
          // console.log(imgRes.data)
          img = document.createElement("img")
          img.src = imgRes.data.message
          imgBox.innerHTML = ""
          imgBox.appendChild(img)
        }
      });
    }
  }
});

getBreedList();
