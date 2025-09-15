import React, { useState, useEffect } from "react";
import { Box, Button, CircularProgress } from "@mui/material";

import useAccommodations from "../../../hooks/useAccommodations.js";
import AccGrid from "../../components/accommodations/Grid/accGrid.jsx";
import Form from "../../components/accommodations/Form/Form";
import Filter from "../../components/accommodations/Filter/Filter.jsx";
import "../style.css";

const AccommodationsPage = () => {
    const { accommodations, loading, onAdd, onEdit, onDelete } = useAccommodations();
    const [addDialogOpen, setAddDialogOpen] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [filtered, setFiltered] = useState([]);

    const categories = [...new Set(accommodations.map(a => a.category))].filter(Boolean);

    useEffect(() => {
        if (selectedCategory === "") {
            setFiltered(accommodations);
        } else {
            setFiltered(accommodations.filter(a => a.category === selectedCategory));
        }
    }, [selectedCategory, accommodations]);

    const handleAddClose = () => setAddDialogOpen(false);

    return (
        <>
            <Box className="accommodations-container">
                {loading ? (
                    <Box className="loading-box">
                        <CircularProgress />
                    </Box>
                ) : (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "center", mb: 2 }}>
                            <Button variant="contained" color="primary" onClick={() => setAddDialogOpen(true)}>
                                Add Accommodation
                            </Button>
                        </Box>

                        <Filter
                            categories={categories}
                            selectedCategory={selectedCategory}
                            onChange={setSelectedCategory}
                        />

                        <AccGrid accommodations={filtered} onEdit={onEdit} onDelete={onDelete} />
                    </>
                )}
            </Box>

            <Form open={addDialogOpen} onClose={handleAddClose} onAdd={onAdd} />
        </>
    );
};

export default AccommodationsPage;
