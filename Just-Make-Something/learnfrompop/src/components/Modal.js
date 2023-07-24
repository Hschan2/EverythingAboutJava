import React, { useState } from 'react'
import { kor } from '../constants/pop';

const Modal = () => {
    const [showModal, setShowModal] = useState(false);
    const getKor = kor.split('\n').map(v => v.trim()).filter(v => v.length > 0);

    const openModal = () => {
        if (showModal === true) setShowModal(false);
        else setShowModal(true);
    }

    const closeModal = () => {
        setShowModal(false);
    }

    return (
        <div>
            <button className='modalButton' onClick={openModal}>한글가사</button>

            {showModal && (
                <div className="modal-container">
                    <div className="modal-backdrop" onClick={closeModal} />
                    <div className="modal-content">
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