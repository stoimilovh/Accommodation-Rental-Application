import React from 'react';
import { useNavigate, useParams } from 'react-router';
import { Box, Typography, Button, CircularProgress, Stack, Paper } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import useAccommodationDetails from '../../../../hooks/useAccommodationDetails.js';

const AccommodationDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const { accommodation, host } = useAccommodationDetails(id);

    if (!accommodation) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Box sx={{ p: 4 }}>
            <Paper elevation={3} sx={{ p: 4, borderRadius: 4 }}>
                <Typography variant="h4" sx={{ mb: 2 }}>
                    {accommodation.name}
                </Typography>

                <Typography variant="subtitle1" sx={{ mb: 1 }}>
                    Category: {accommodation.category}
                </Typography>

                <Typography variant="subtitle1" sx={{ mb: 1 }}>
                    Number of Rooms: {accommodation.numRooms}
                </Typography>

                <Typography variant="subtitle1" sx={{ mb: 1 }}>
                    Rented: {accommodation.rented ? 'Yes' : 'No'}
                </Typography>

                <Typography variant="subtitle1" sx={{ mb: 1 }}>
                    Host: {host?.name || 'Unknown'}
                </Typography>

                <Stack direction="row" justifyContent="flex-end" sx={{ mt: 4 }}>
                    <Button
                        variant="outlined"
                        startIcon={<ArrowBackIcon />}
                        onClick={() => navigate('/accommodations')}
                    >
                        Back to List
                    </Button>
                </Stack>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
