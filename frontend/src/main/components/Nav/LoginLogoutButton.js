import { Button, Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import { hasRole } from "main/utils/currentUser";
import AppNavbarLocalhost from "main/components/Nav/AppNavbarLocalhost"

export default function LoginLogoutButton({ currentUser, systemInfo, doLogout }) {
    console.log("systemInfo",systemInfo);
    const loginUrl =  (systemInfo.activeProfiles==="e2etests") ? "/login" : "/oauth2/authorization/google";    
    return (
        <Nav className="ml-auto">
            {
                currentUser && currentUser.loggedIn ? (
                    <>
                        <Navbar.Text className="me-3" as={Link} to="/profile">Welcome, {currentUser?.root?.user?.email}</Navbar.Text>
                        <Button onClick={doLogout}>Log Out</Button>
                    </>
                ) : (
                    <Button href={loginUrl}>Log In</Button>
                )
            }
        </Nav>
    );
}