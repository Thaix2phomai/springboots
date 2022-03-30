let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 6
function addProduct (arr, newName, newPrice, newBrand, newCount){
    let newProduct = {
        name: newName,
        price: newPrice,
        brand: newBrand,
        count: newCount
    }
    arr.push(newProduct)
    return arr

}

console.log(addProduct(products, "ádas", 195000, "Cheese", 2))


//7
function deleteProduct (arr){
    let brand = "Samsung"
    return arr.filter(product => product.brand != brand )
}

console.log(deleteProduct(products))


//8

function sortbyprice1 (arr) {
    return arr.sort((a,b) => a.price - b.price)
}
console.log(sortbyprice1(products))

//9

function sortbyprice2 (arr) {
    return arr.sort((a,b) => b.price - a.price)
}
console.log(sortbyprice2(products))


//10

function getTwoRandonProduct(arr) {
    let newArr = []
    while (newArr.length < 2){
        let randomNum = Math.floor(Math.random() * arr.length);
        if(!newArr.includes(arr[randomNum])) {
            newArr.push(arr[randomNum]);
        }
    }
   
    return newArr;
}
console.log(getTwoRandonProduct(products));

