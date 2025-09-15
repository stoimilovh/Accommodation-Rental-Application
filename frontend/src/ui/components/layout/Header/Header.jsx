import React from 'react';
import { NavLink, useLocation } from "react-router-dom";
import { AppBar, Box, Button, IconButton, Toolbar, Typography } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import "./Header.css";

const pages = [
    { path: "/", name: "home" },
    { path: "/accommodations", name: "accommodations" },
    { path: "/countries", name: "countries" },
    { path: "/hosts", name: "hosts" },
];

const Header = () => {
    const location = useLocation();

    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ mr: 3 }}>
                        Rent App
                    </Typography>
                    <Box className="nav-links-container">
                        {pages.map((page) => {
                            const isActive = location.pathname === page.path;
                            return (
                                <Button
                                    key={page.name}
                                    component={NavLink}
                                    to={page.path}
                                    sx={{
                                        color: 'white',
                                        textTransform: 'uppercase',
                                        fontWeight: isActive ? 'bold' : 'normal',
                                        borderBottom: isActive ? '5px solid white' : '5px solid transparent',
                                        paddingBottom: '5px 0 px',
                                    }}
                                >
                                    {page.name}
                                </Button>
                            );
                        })}
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;
