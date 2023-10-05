const btn = document.querySelector(".update-profile")
const form = document.querySelector(".update")
const first = document.querySelector(".hfirst")
const last = document.querySelector(".hlast")
const dive = document.querySelector(".updateprofilediv")

btn.addEventListener("click", () => {
    form.innerHTML = `
            <form class="container" action="/user-profile" method="post" th:object="${'$' + '{user}'}">
                <div class="form-group f1">
                  <label for="firstName">First Name</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" name="firstName" value="${first.value}" aria-describedby="emailHelp" placeholder="Enter First Name">
                </div>
                <div class="form-group f1">
                  <label for="lastName">Last Name</label>
                  <input type="text" class="form-control" id="exampleInputEmail1" name="lastName" value="${last.value}" aria-describedby="emailHelp" placeholder="Enter Last Name">
                </div>
                <div class="form-group f1">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group f1">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
                  <small id="emailHelp" class="form-text text-muted">We'll never share your info with anyone else.</small>
                </div>
                <button type="submit" class="btn btn-primary f1">Update Profile</button>
            </form>
    `

    dive.innerHTML = ''
})







// `
// <label for="firstName">First Name: </label>
// <input type="text" name="firstName" value="${first.value}">
// <label for="lastName">Last Name: </label>
// <input type="text" name="lastName" value="${last.value}">
// <label for="email">Email: </label>
// <input type="email" name="email" value="">
// <label for="password">Password: </label>
// <input type="password" name="password" value="" placeholder="Enter your password">
// <button type="submit">Update Profile</button>
// `
