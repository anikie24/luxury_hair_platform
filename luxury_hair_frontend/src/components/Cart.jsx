import React, { useEffect, useState } from "react";
import { Link,useNavigate } from "react-router-dom";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate(); 

  useEffect(() => {
    const cart = localStorage.getItem("cart");
    if (cart) {
      setCartItems(JSON.parse(cart));
    }
  }, []);

  const calculateTotalPrice = () => {
    return cartItems.reduce(
      (total, item) => total + item.hairPrice * item.quantity,
      0
    );
  };

  const handleRemoveItem = (
    productId,
    selectedLength,
    selectedColor,
    selectedStyle
  ) => {
    const updatedCart = cartItems.filter(
      (item) =>
        !(
          item.productId === productId &&
          item.selectedLength === selectedLength &&
          item.selectedColor === selectedColor &&
          item.selectedStyle === selectedStyle
        )
    );
    setCartItems(updatedCart);
    localStorage.setItem("cart", JSON.stringify(updatedCart));
  };
  const handleCheckout = () => {
    navigate("/Shipping"); 

  if (cartItems.length === 0) {
    return (
      <div className="flex flex-col items-center mt-10">
        <h2 className="text-2xl font-semibold text-black mb-4">
          Your Cart is Empty
        </h2>
        <Link to="/products" className="text-lg text-black hover:underline">
          Continue Shopping
        </Link>
      </div>
    );
  }

  return (
    <div className="max-w-5xl mx-auto mt-4">
      <h2 className="text-3xl font-bold mb-6 text-center text-black">
        Your Cart
      </h2>
      <table className="min-w-full bg-white text-black border border-black rounded-lg shadow-lg overflow-hidden">
        <thead className="bg-black text-white">
          <tr>
            <th className="p-4 text-left">Product</th>
            <th className="p-4 text-left">Length</th>
            <th className="p-4 text-left">Color</th>
            <th className="p-4 text-left">Style</th>
            <th className="p-4 text-left">Quantity</th>
            <th className="p-4 text-left">Price</th>
            <th className="p-4 text-left">Remove</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item, index) => (
            <tr key={index} className="border-b border-black">
              <td className="p-4">{item.hairStyle}</td>
              <td className="p-4">{item.selectedLength}</td>
              <td className="p-4">{item.selectedColor}</td>
              <td className="p-4">{item.selectedStyle}</td>
              <td className="p-4">{item.quantity}</td>
              <td className="p-4">R{item.hairPrice * item.quantity}</td>
              <td className="p-4">
                <button
                  onClick={() =>
                    handleRemoveItem(
                      item.productId,
                      item.selectedLength,
                      item.selectedColor,
                      item.selectedStyle
                    )
                  }
                  className="bg-black text-white px-3 py-1 rounded hover:bg-gray-800 transition"
                >
                  Remove
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="mt-6 flex justify-between items-center">
        <div>
          <Link to="/products" className="text-black hover:underline text-lg">
            Continue Shopping
          </Link>
        </div>
        <div className="text-xl font-semibold text-black">
          Total: R{calculateTotalPrice()}
        </div>
        <div>
          <button
            onClick={() => alert("Proceed to checkout")}
            className="bg-black text-white px-6 py-2 rounded hover:bg-gray-800 transition"
          >
            Checkout
          </button>
        </div>
      </div>
    </div>
  );
};
};

export default Cart;
