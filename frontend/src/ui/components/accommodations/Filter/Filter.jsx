import React from "react";
import { ToggleButton, ToggleButtonGroup, Box } from "@mui/material";

const CategoryFilter = ({ categories, selectedCategory, onChange }) => {
    return (
        <Box sx={{ display: "flex", justifyContent: "center", mb: 3, flexWrap: "wrap" }}>
            <ToggleButtonGroup
                value={selectedCategory}
                exclusive
                onChange={(e, newCategory) => {
                    if (newCategory !== null) onChange(newCategory);
                }}
                sx={{ flexWrap: "wrap" }}
            >
                <ToggleButton value="">All</ToggleButton>
                {categories.map((cat, i) => (
                    <ToggleButton key={i} value={cat}>
                        {cat}
                    </ToggleButton>
                ))}
            </ToggleButtonGroup>
        </Box>
    );
};

export default CategoryFilter;
