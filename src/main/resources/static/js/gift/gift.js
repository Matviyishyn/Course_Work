const giftNodeList = document.querySelectorAll(".gift");

giftNodeList.forEach(gift => {
    let id = gift.getAttribute("data-id");
    console.log(id);
    let detailsBtn = gift.querySelector(".details");
    let editBtn = gift.querySelector(".edit");
    let deleteBtn = gift.querySelector(".delete");

    detailsBtn.addEventListener("click", () => {
       window.location = `/gift/${id}`;
    });

    editBtn.addEventListener("click", () => {
       window.location = `/gift/edit/${id}`;
    });

    deleteBtn.addEventListener("click", () => {
        deleteGift(gift);
    });
});

function deleteGift(gift){
    let id = gift.getAttribute("data-id");
    console.log(id);

    const response = fetch(`/gift/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow'
    }).then(response => (window.location = "/gift"))
}