import axiosInstance from "../axios/axios.js";

const countryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/countries");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/countries/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/countries", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/countries/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/countries/${id}`);
    },
};

export default countryRepository;
