let form = document.el
let login = form.querySelector('.login')
let password = form.querySelector('.password')
let fields = form.querySelector('.field')

form.addEventListener('submit', function (event) {
    event.preventDefault()
    console.log("sdf")
    console.log("name:", name.nodeValue)
    for(let i = 0; i < fields.length; i++) {
        if(!fields[i].value) {
            console.log("field is blank!", fields[i])
        }
    }
})
