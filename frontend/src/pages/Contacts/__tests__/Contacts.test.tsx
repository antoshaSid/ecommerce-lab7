import React from "react";

import { mountWithStore } from "../../../utils/test/testHelper";
import Contacts from "../Contacts";

window.scrollTo = jest.fn();

describe("Contacts", () => {
    it("should render correctly", () => {
        const wrapper = mountWithStore(<Contacts />);
        expect(wrapper.text().includes("Working time")).toBe(true);
        expect(wrapper.text().includes("The online store is open from 08:00 to 20:00 without breaks and weekends.")).toBe(true);
        expect(wrapper.text().includes("Online orders are accepted around the clock.")).toBe(true);
        expect(wrapper.text().includes("Delivery")).toBe(true);
        expect(wrapper.text().includes("1-3 business days (delivery times may vary depending on the remoteness of the location).")).toBe(true);
        expect(wrapper.text().includes("5 business days.")).toBe(true);
        expect(wrapper.text().includes("1-3 business days (delivery times may vary depending on the remoteness of the location).")).toBe(true);
    });
});
