import React, {FC, ReactElement, useEffect} from "react";
import { Col, Row, Typography } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import ContentTitle from "../../components/ContentTitle/ContentTitle";

const Contacts: FC = (): ReactElement => {

    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    
    return (
        <ContentWrapper>
            <ContentTitle icon={<InfoCircleOutlined />} title={"Contacts"} />
            <Row gutter={32}>
                <Col span={12}>
                    <div>
                        <Typography.Text strong>{"Mobile: "}</Typography.Text>
                        <Typography.Text>(050) 777-77-77</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>antosha123ememdems@gmail.com</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Working time</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            The online store is open from 08:00 to 20:00 without breaks and weekends. <br />
                            Online orders are accepted around the clock.
                        </Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Delivery time</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>1-3 business days (delivery times may vary depending on the remoteness of the location).</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Storage period</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>5 business days.</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Delivery cost</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>From 2 USD.</Typography.Text>
                    </div>
                </Col>
            </Row>
        </ContentWrapper>
    );
};

export default Contacts;
