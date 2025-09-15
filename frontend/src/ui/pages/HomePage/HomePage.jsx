import React from 'react';
import {Box, Container, Typography} from "@mui/material";
import "./HomePage.css";
import backgroundImg from './img.jpg';

const HomePage = () => {
    return (
        <Box sx={{m:0, p:0}}>
            <img src={backgroundImg} alt="background" className="background-img" />
            <Container maxWidth="md" className="home-text">
                <Typography variant="h4" gutterBottom>
                    Welcome to Accommodation Rental Application!
                </Typography>
                <Typography variant="h6" className="author-text">
                    Made by Hristijan Stoimilov
                </Typography>
            </Container>
        </Box>
    );
};

export default HomePage;
