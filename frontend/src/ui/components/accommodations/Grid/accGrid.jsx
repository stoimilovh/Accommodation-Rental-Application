import React from "react";
import { Grid } from "@mui/material";
import AccCard from "../Card/accCard.jsx";
import './Grid.css';

const AccGrid = ({ accommodations, onEdit, onDelete }) => {

    return (
        <Grid container spacing={{ xs: 2, md: 3 }}>
            {accommodations.map((accommodation) => (
                <Grid item key={accommodation.id ?? Math.random()} xs={12} sm={6} md={4} lg={3}>
                    <AccCard
                        accommodation={accommodation}
                        onEdit={onEdit}
                        onDelete={onDelete}
                    />
                </Grid>
            ))}
        </Grid>
    );
};

export default AccGrid;
