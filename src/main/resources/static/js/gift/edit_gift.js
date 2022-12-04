const giftForm = document.querySelector(".gift-form");
const submitBtn = document.querySelector(".submit-form");

const removeCandyList = document.querySelector(".remove-candy-list");
const addCandyList = document.querySelector(".add-candy-list");

const deleteCandies = removeCandyList.querySelectorAll(".candy");
const addCandies = addCandyList.querySelectorAll(".candy");

submitBtn.addEventListener("click", sendData);


deleteCandies.forEach(candy => candy.addEventListener("click", () => {
    candy.classList.toggle("selected");
}));

addCandies.forEach(candy => candy.addEventListener("click", () => {
    candy.classList.toggle("selected");
}));

function sendData(){
    let addArea = document.querySelector(".add-candies-area");
    let deleteArea = document.querySelector(".delete-candies-area");

    let addCandiesList = [];
    let deleteCandiesList = [];

    addCandies.forEach(candy => {
        if(candy.classList.contains("selected")){
            let id = candy.getAttribute("data-id");
            addCandiesList.push(id);
        }
    });

    deleteCandies.forEach(candy => {
        if(candy.classList.contains("selected")){
            let id = candy.getAttribute("data-id");
            deleteCandiesList.push(id);
        }
    });

    console.log(addCandiesList);
    console.log(deleteCandiesList);

    addArea.innerHTML = addCandiesList.toString();
    deleteArea.innerHTML = deleteCandiesList.toString();

    giftForm.submit();
}