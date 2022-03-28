//bài 1
function calculateFactorial(n) {
  if (n > 0) {
    return n * calculateFactorial(n - 1);
  } else {
    return 1;
  }
}

console.log(calculateFactorial(5));

//bài 2
function reverseString(str) {
  var newString = "";
  for (var i = str.length - 1; i >= 0; i--) {
    newString = newString + str[i];
  }

  return newString;

}

console.log(reverseString("hello"));

//bài 3

function translate(country) {
  switch (country) {
    case "VN":
      console.log("Xin chào");
      break;
    case "EN":
      console.log("Hello");
      break;
    case "JP":
      console.log("Konichiwa");
      break;
    case "TH":
      console.log("Sawadee");
      break;
    case "FR":
      console.log("Bonjour");
      break;
    default:
      break;
  }
}

console.log(translate("EN"))

//bài 4
 function subString (str){
    let stringSlice = str.substring(0,10) + "...";
    return stringSlice;
 }

 console.log(subString("quwelkrwjhelkrhernw"))
