const giftForm = document.querySelector(".gift-form");
const submitBtn = document.querySelector(".submit-form");

const candyList = document.querySelectorAll(".candy");
const availableCandies = [...candyList];

submitBtn.addEventListener("click", sendData);

availableCandies.forEach(candy => candy.addEventListener("click", () => {
    candy.classList.toggle("selected");
}));

function sendData(){
    let candyListArea = document.querySelector(".candy-list");
    let selectedCandies = [];

    availableCandies.forEach(candy => {
        if(candy.classList.contains("selected")){
            let id = candy.getAttribute("data-id");
            selectedCandies.push(id);
        }
    });

    candyListArea.innerHTML = selectedCandies.toString();
    giftForm.submit();
}

