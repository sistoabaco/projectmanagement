export const state = () => ({
    idEmployee: 0,
    firstName: "",
    lastName: "",
    gender: "",
    address: "",
    cellphone: "",
    email: "",
    provenance: ""
})

export const mutations = {
    storeIdEmployee: (state, data) =>{
        state.idEmployee = data
    },
    storeFirstName: (state, data) =>{
        state.firstName = data
    },
    storeLastName: (state, data) =>{
        state.lastName = data
    },
    storeGender: (state, data) =>{
        state.gender = data
    },
    storeAddress: (state, data) =>{
        state.address = data
    },
    storeCellphone: (state, data) =>{
        state.cellphone = data
    },
    storeEmail: (state, data) =>{
        state.email = data
    },
    storeProvenance: (state, data) =>{
        state.provenance = data
    }
}