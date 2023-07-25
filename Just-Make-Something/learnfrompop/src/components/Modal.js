import React, { useState } from 'react'
import { kor } from '../constants/pop';

const Modal = () => {
    const [showModal, setShowModal] = useState(false);
    const [dragging, setDragging] = useState(false);
    const [posX, setPosX] = useState(0);
    const [posY, setPosY] = useState(0);
    const getKor = kor.split('\n').map(v => v.trim()).filter(v => v.length > 0);

    const openModal = () => {
        if (showModal === true) setShowModal(false);
        else setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    const handleMouseDown = (e) => {
        setDragging(true);
        setPosX(e.clientX);
        setPosY(e.clientY);
    };

    const handleMouseUp = () => {
        setDragging(false);
    };

    const handleMouseMove = (e) => {
        if (dragging) {
            const deltaX = e.clientX - posX;
            const deltaY = e.clientY - posY;

            setPosX(e.clientX);
            setPosY(e.clientY);

            const modalContent = document.querySelector('.modal-content');
            const rect = modalContent.getBoundingClientRect();

            modalContent.style.left = rect.left + deltaX + 'px';
            modalContent.style.top = rect.top + deltaY + 'px';
        }
    };

    return (
        <div>
            <button className='modalButton' onClick={openModal}>한글가사</button>

            {showModal && (
                <div
                    className="modal-container"
                    onMouseUp={handleMouseUp}
                >
                    <div className="modal-backdrop" onClick={closeModal} />
                    <div
                        className="modal-content"
                        onMouseDown={handleMouseDown}
                        onMouseMove={handleMouseMove}
                    >
                        <button className="close" onClick={closeModal}>&times;</button>
                        {getKor.map((text, index) => (
                            <p key={index} className='kr'>{text}</p>
                        ))}
                    </div>
                </div>
            )}
        </div>
    )
}

export default Modal