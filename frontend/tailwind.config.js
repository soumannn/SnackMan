/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'bisque': 'rgb(255,228,196)', //Button-Primärfarbe
        'hover-bisgue': 'rgb(247,194,130)', //Button-Primärfarbe-Hover
        'text-color-heading': 'rgb(0,0,0)' //Textfarbe Überschrift
      },
      spacing: {
        'input-height': '44px', //Inputfeld Höhe
        'bottom-heading-marging': '40px'
      },
      fontFamily: {
        'primary-font': ['Inter', 'sans-serif'],
        'heading-font': ['Poppins', 'sans-serif']
      },
      fontSize: {
        'sm': '0.875rem', //14px
        'base': '1rem', //16px
        'lg': '1.5rem', //24px
        'heading': '4rem' //40px
      },
      backgroundImage: {
        'custom-bg': "url('/src/assets/TestBackground1.jpg')"
      }
    },
  },
  plugins: [],
}