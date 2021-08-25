let activeCustomer = {}
$(function(){

    loadAllAccounts();

    $("#openNewAccount").click(openNewAccount)
    $("#loginBtn").click(loginToAccount)
    $("#confirmDeposit").click(depositMoney)
    $("#confirmWithdrawal").click(withdrawMoney)
    $("#logoutBtn").click(logout)
})

const loadAllAccounts = () => {
    axios.get(getAllAccounts)
        .then((response) => {
            console.log(response.data)
        })
}

const openNewAccount = () => {
    let holderName = $("#newAccountHolder").val();

    if(holderName) {
        axios.post(addAccount+holderName)
            .then((response) => {
                loadUserAccount(response.data.id, response.data.holder)
            })
            .catch((error) => {
                if(error.response) {
                    swal("Ett fel uppstod!", error.response.data.message, "error")
                } else {
                    console.log(error)
                }
            })

    } else  {
        swal("Ett fel uppstod! ","Du måste ange ett namn!", "error")
    }
}

const loginToAccount = () => {
    let name = $("#holderNameLogin").val();
    let account = $("#accountNumberLogin").val();

    loadUserAccount(account, name)
}

const loadUserAccount = (holderId, holder) => {
    axios.get(login+holderId + "&holder=" +holder)
        .then((response) => {
            renderAccount(response.data)
        })
        .catch((error) => {
            if(error.response) {
                swal("Ett fel uppstod!", error.response.data.message, "error")
            } else {
                console.log(error)
            }
        })
}

const renderAccount = (account) => {
    sessionStorage.setItem("customer", JSON.stringify(account))
    $("#loginModal").modal("hide")
    $("#holderNameLogin").val("");
    $("#accountNumberLogin").val("");
    $(".lower-container").hide();
    $("#name-heading").text(account.holder);
    $(".user-container").show();
    renderInfo(account)

}

const renderInfo = (account) => {
    sessionStorage.setItem("customer", JSON.stringify(account))
    $("#accountNumber").text(account.id)
    $("#accountBalance").text(account.balance)
}

const depositMoney = () => {
    activeCustomer = JSON.parse(sessionStorage.getItem("customer"));
    let amount = $("#depositAmount").val();
    if(amount) {
        axios.post(deposit+activeCustomer.id + "&amount=" + amount)
            .then((response) => {
                $("#depositAmount").val("");
                swal("Insättning lyckades!", "Du har satt in " + amount + " kr.", "success")
                    .then(() => {
                        renderInfo(response.data)
                    })

            })
            .catch((error) => {
                if(error.response) {
                    swal("Ett fel uppstod!", error.response.data.message, "error");
                } else {
                    console.log(error)
                }
            })

    } else {
        swal("Belopp saknas!", "Du måste ange vilket belopp du vill sätta in!", "error")
    }
}

const withdrawMoney = () => {
    activeCustomer = JSON.parse(sessionStorage.getItem("customer"));
    let amount = $("#withdrawAmount").val();
    if(amount) {

        axios.post(withdraw+activeCustomer.id + "&amount=" + amount)
            .then((response) => {
                $("#withdrawAmount").val("");
                swal("Uttag avklarat!", "Du har tagit ut " + amount + " kr.", "success")
                    .then(() => {
                        renderInfo(response.data)
                    })

            })
            .catch((error) => {
                if(error.response) {
                    swal("Ett fel uppstod!", error.response.data.message, "error");
                } else {
                    console.log(error)
                }
            })

    } else {
        swal("Belopp saknas!", "Du måste ange vilket belopp du vill ta ut!", "error")
    }
}

const logout = () => {

    sessionStorage.removeItem("customer")
    $("#name-heading").text("till Best Bank Ever");
    $(".lower-container").show();
    $(".user-container").hide();

}