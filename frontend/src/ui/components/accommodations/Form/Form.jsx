import React, { useState } from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useHosts from "../../../../hooks/useHosts.js";

const initialFormData = {
    name: "",
    category: "",
    host: "",
    numRooms: "",
};

const AddAccommodationDialog = ({ open, onClose, onAdd }) => {
    const [formData, setFormData] = useState(initialFormData);
    const { hosts } = useHosts();

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add New Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Accommodation Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Accommodation Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Accommodation Category"
                        variant="outlined">
                        <MenuItem value="ROOM">Room</MenuItem>
                        <MenuItem value="HOUSE">House</MenuItem>
                        <MenuItem value="FLAT">Flat</MenuItem>
                        <MenuItem value="APARTMENT">Apartment</MenuItem>
                        <MenuItem value="HOTEL">Hotel</MenuItem>
                        <MenuItem value="MOTEL">Motel</MenuItem>
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="host"
                        value={formData.host}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>
                                {host.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    margin="dense"
                    label="Number of Rooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAccommodationDialog;
