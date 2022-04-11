
import React from 'react';

import { currentUserFixtures } from "fixtures/currentUserFixtures"
import LoginLogoutButton from "main/components/Nav/LoginLogoutButton";

export default {
    title: 'components/Nav/LoginLogoutButton',
    component: LoginLogoutButton
};

const Template = (args) => {
    return (
        <LoginLogoutButton {...args} />
    )
};

export const productionLoggedOut = Template.bind({});
productionLoggedOut.args = {
    systemInfo: { activeProfile: "production" }
};

export const e2eTestsLoggedOut = Template.bind({});
e2eTestsLoggedOut.args = {
    systemInfo: { activeProfile: "e2etests" }
};

export const productionLoggedIn = Template.bind({});
productionLoggedIn.args = {
    systemInfo: { activeProfile: "production" },
    currentUser: currentUserFixtures.adminUser
};

export const e2eTestsLoggedIn = Template.bind({});
e2eTestsLoggedIn.args = {
    systemInfo: { activeProfile: "e2etests" },
    currentUser: currentUserFixtures.adminUser
};