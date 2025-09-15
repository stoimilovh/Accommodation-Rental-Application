import React, { useState } from "react";
import InfoIcon from "@mui/icons-material/Info";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { Box, Button, Card, CardActions, CardContent, Typography } from "@mui/material";
import { useNavigate } from "react-router";
import Delete from "../Delete/Delete.jsx";
import './Card.css';

const AccCard = ({ accommodation, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1, width: 300 }}>
                <CardContent>
                    <Typography variant="h5" sx={{ mb: 1, display: "flex", justifyContent: "center" }}>{accommodation.name}</Typography>
                    <Typography variant="subtitle2" sx={{ mb: 1, display: "flex", justifyContent: "center" }}>
                        {accommodation.category || "No description available."}
                    </Typography>
                    <Typography variant="h5" sx={{ mb: 1, display: "flex", justifyContent: "center" }}>{accommodation.id}</Typography>

                </CardContent>
                <CardActions sx={{ justifyContent: "space-between" }}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon />}
                        onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        {/*<Button*/}
                        {/*    size="small"*/}
                        {/*    color="warning"*/}
                        {/*    startIcon={<EditIcon />}*/}
                        {/*    sx={{ mr: "0.25rem" }}*/}
                        {/*    onClick={() => setEditDialogOpen(true)}*/}
                        {/*>*/}
                        {/*    Edit*/}
                        {/*</Button>*/}
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <Delete
                open={deleteDialogOpen}
                onClose={() => setDeleteDialogOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>
    );
};

export default AccCard;
