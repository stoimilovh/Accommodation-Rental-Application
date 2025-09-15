import React, { useState } from "react";
import { Box, Button, CircularProgress } from "@mui/material";
import useHosts from "../../../hooks/useHosts.js";
// import HostsGrid from "../../components/hosts/HostsGrid/HostsGrid.jsx";
// import AddHostDialog from "../../components/hosts/AddHostDialog/AddHostDialog.jsx";
import "../style.css";

const HostsPage = () => {
    const { hosts, loading, onAdd, onEdit, onDelete } = useHosts();
    const [addDialogOpen, setAddDialogOpen] = useState(false);

    return (
        <>
            <Box className="hosts-box">
                {loading ? (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                ) : (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: 2 }}>
                            <Button variant="contained" onClick={() => setAddDialogOpen(true)}>
                                Add Host
                            </Button>
                        </Box>
                        {/*<HostsGrid hosts={hosts} onEdit={onEdit} onDelete={onDelete} />*/}
                    </>
                )}
            </Box>
            {/*<AddHostDialog*/}
            {/*    open={addDialogOpen}*/}
            {/*    onClose={() => setAddDialogOpen(false)}*/}
            {/*    onAdd={onAdd}*/}
            {/*/>*/}
        </>
    );
};

export default HostsPage;
