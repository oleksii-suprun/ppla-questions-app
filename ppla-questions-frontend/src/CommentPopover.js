import React, { useState, useRef } from 'react';
import { OverlayTrigger, Popover, PopoverBody } from 'react-bootstrap';

const CommentPopover = ({ comment }) => {
    const [popoverOpen, setPopoverOpen] = useState(false);
    const iconRef = useRef(null);

    const handleToggle = (nextShow) => {
        setPopoverOpen(nextShow);
    };

    const popover = (
        <Popover id="popover-basic">
            <PopoverBody>
                {comment}
            </PopoverBody>
        </Popover>
    );

    return (
        <>
            {comment && (
                <OverlayTrigger
                    trigger="click"
                    placement="bottom"
                    overlay={popover}
                    rootClose
                    show={popoverOpen}
                    onToggle={handleToggle}
                >
                    <i
                        ref={iconRef}
                        className="bi bi-exclamation-triangle text-warning"
                        style={{ cursor: 'pointer', position: 'relative', zIndex: 2 }}
                    ></i>
                </OverlayTrigger>
            )}

            {/* Blurred Overlay */}
            {popoverOpen && (
                <div
                    style={{
                        position: 'fixed',
                        top: 0,
                        left: 0,
                        right: 0,
                        bottom: 0,
                        backgroundColor: 'rgba(0, 0, 0, 0.2)',
                        zIndex: 1,
                    }}
                    onClick={() => setPopoverOpen(false)} // Close popover if the overlay is clicked
                />
            )}
        </>
    );
};

export default CommentPopover;
