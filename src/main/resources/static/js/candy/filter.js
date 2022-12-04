const searchInput = document.querySelector(".search-input");
const filtersCandyList = document.querySelectorAll(".candy");
const candies = [...filtersCandyList];

const filterToggleBtn = document.querySelector(".filter button");
const filterForm = document.querySelector(".filter-form");

searchInput.addEventListener("change", displayMatches);
searchInput.addEventListener("keyup", displayMatches);

filterToggleBtn.addEventListener("click", () => {
    filterForm.classList.toggle("hidden");
});

function findMatches(wordToMatch){
    return candies.filter(candy => {
        const regex = new RegExp(wordToMatch, 'gi');

        const name = candy.querySelector(".name").innerText;
        const brand = candy.querySelector(".brand").innerText;
        return !(name.match(regex) || brand.match(regex));
    });
}

function displayMatches(){
    console.log("hello");
    const matches = findMatches(this.value);
    candies.forEach(element => element.classList.remove('hidden'));
    matches.forEach(element => element.classList.add("hidden"));
}