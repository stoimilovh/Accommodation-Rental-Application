import { useEffect, useState } from "react";
import accommodationRepository from "../repository/accommodationRepository.js";
import hostRepository from "../repository/hostRepository.js";

const useAccommodationDetails = (id) => {
    const [state, setState] = useState({
        accommodation: null,
        host: null

    });

    useEffect(() => {
        accommodationRepository
            .findById(id)
            .then((response) => {
                setState((prev) => ({ ...prev, accommodation: response.data }));
                console.log(response.data);
                hostRepository
                    .findById(response.data.host)
                    .then((res) => {
                        setState((prev) => ({ ...prev, host: res.data }));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useAccommodationDetails;
