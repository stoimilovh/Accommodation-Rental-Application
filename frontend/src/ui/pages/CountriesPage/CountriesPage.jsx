import React, { useState } from "react";
import { Box, Button, CircularProgress } from "@mui/material";
import useCountries from "../../../hooks/useCountries.js";
// import CountriesGrid from "../../components/countries/CountriesGrid/CountriesGrid.jsx";
// import AddCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx";
import "../style.css";

const CountriesPage = () => {
    const { countries, loading, onAdd, onEdit, onDelete } = useCountries();
    const [addDialogOpen, setAddDialogOpen] = useState(false);

    return (
        <>
            <Box className="countries-box">
                {loading ? (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                ) : (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: 2 }}>
                            <Button variant="contained" onClick={() => setAddDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                        {/*<CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete} />*/}
                    </>
                )}
            </Box>
            {/*<AddCountryDialog*/}
            {/*    open={addDialogOpen}*/}
            {/*    onClose={() => setAddDialogOpen(false)}*/}
            {/*    onAdd={onAdd}*/}
            {/*/>*/}
        </>
    );
};

export default CountriesPage;
