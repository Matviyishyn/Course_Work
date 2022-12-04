const addCandyButton = document.querySelector("#add-candy-btn");

const addCandyWindow = document.querySelector(".add-candy-window");
const addCandyForm = document.querySelector(".add-candy-form");
const submitFormBtn = addCandyForm.querySelector(".submit-candy");

const closeAddCandyFormButton = document.querySelector(".close");

const candyList = document.querySelectorAll(".candy");

addCandyButton.addEventListener("click", toggleAddForm);

closeAddCandyFormButton.addEventListener("click", () => {
    clearForm();
    addCandyWindow.classList.toggle("visible");
    closeAddCandyFormButton.classList.toggle("visible");
});

submitFormBtn.addEventListener("click", () => {
    addCandyForm.submit();
});

candyList.forEach( candy => {
    let updateBtn = candy.querySelector(".update");
    let deleteBtn = candy.querySelector(".delete");

    updateBtn.addEventListener("click", () => {
        openUpdateForm(candy);
    });

    deleteBtn.addEventListener("click", () => {
        deleteCandy(candy);
    });
});

function toggleAddForm(){
    addCandyWindow.classList.toggle("visible");
    closeAddCandyFormButton.classList.toggle("visible");
}

function openUpdateForm(candy){
    let id = candy.getAttribute("data-id");
    let name = candy.querySelector(".name").innerText;
    let brand = candy.querySelector(".brand").innerText;
    let imageUrl = candy.querySelector("img").src;
    let price = candy.querySelector(".price").innerText;
    console.log(price);
    let weight = candy.querySelector(".weight").innerText;
    let sugar = candy.querySelector(".sugar-amount").innerText;

    addCandyForm.querySelector("input[name='name']").value = name;
    addCandyForm.querySelector("input[name='brand']").value = brand;
    addCandyForm.querySelector("input[name='imageUrl']").value = imageUrl;
    addCandyForm.querySelector("input[name='price']").value = price;
    addCandyForm.querySelector("input[name='weight']").value = weight;
    addCandyForm.querySelector("input[name='sugar']").value = sugar;
    addCandyForm.querySelector(".submit-candy").innerHTML = "Update";
    addCandyForm.setAttribute("action", `/candy/edit/${id}`);
    toggleAddForm();
}

function clearForm(){
    addCandyForm.querySelector(".submit-candy").innerHTML = "Submit";
    addCandyForm.setAttribute("action", `/candy/new`);
    addCandyForm.reset();
}

function deleteCandy(candy){
    let id = candy.getAttribute("data-id");
    console.log(id);

    const response = fetch(`/candy/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow'
    }).then(response => (window.location = "/candy"))
}